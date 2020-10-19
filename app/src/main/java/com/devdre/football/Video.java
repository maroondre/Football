package com.devdre.football;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.devdre.football.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Video extends Fragment {

    private TextView title,date;
    private ImageView image;
    private ListView listView;
    private RecyclerView rView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;
    private ProgressDialog pdLoading;
    private List<ListVideo> llp;
    private BottomNavigationView bottomNavigationView;
    private View view;
    Connection connection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.activity_video, container, false);
        getActivity().setTitle("Live Football Matches");
        connection = new Connection();
        if(connection.isConnected(getActivity())) {
            declare();
            call();
        }
        return view;

    }

    private void declare()
    {
        title = (TextView) view.findViewById(R.id.vtxtview);
        rView = (RecyclerView) view.findViewById(R.id.vView);
//        rView.setHasFixedSize(true);
        rvLayout = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(rvLayout);

    }

    private void call()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URLPOD)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<ListVideo>> call = api.getVideo();

        call.enqueue(new Callback<List<ListVideo>>() {
            @Override
            public void onResponse(Call<List<ListVideo>> call, Response<List<ListVideo>> response) {
                List<ListVideo> heroList = response.body();

                //Creating a String array for the ListView
                String[] heroes = new String[heroList.size()];

                llp = new ArrayList<>();

                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getTitle();
                    llp.add(new ListVideo(heroList.get(i).getTitle(), heroList.get(i).getEmbed(), heroList.get(i).getDate(), heroList.get(i).getThumbnail()));
                }

                pdLoading = new ProgressDialog(getActivity());
                pdLoading.setMessage("\tPlease Wait...");
                pdLoading.setProgress(10);
                pdLoading.setCancelable(false);
                pdLoading.show();
                mAdapter = new VideoAdapter(getActivity(), llp);
                rView.setAdapter(mAdapter);
                pdLoading.dismiss();
                SnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(rView);


            }

            @Override
            public void onFailure(Call<List<ListVideo>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}