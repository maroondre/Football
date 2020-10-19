package com.devdre.football;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devdre.football.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Score extends Fragment {
    TextView scoretxt;
    Connection connection;
    private CarouselView carouselView;
    private RecyclerView rView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;
    private List<ListScore.Data.Matches> score;
    private List<ListVideo> llp;
    public ArrayList<String> llvpics = new ArrayList<>();
    private ProgressDialog pdLoading;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.activity_score, container, false);
        getActivity().setTitle("Live Score");
        connection = new Connection();
        if(connection.isConnected(getActivity())){
            declare();
            call();
            carView();
        }

        return view;
    }

    public void showPics()
    {
        System.out.println(llvpics.toString());
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + llvpics.size());

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {

                ArrayList<String> imageListTitle = new ArrayList<String>();
                for(int i = 0; i < llvpics.size(); i++ ){
                    imageListTitle.add(llvpics.get(i));
                }
                //imageView.setImageResource(imagelist[position]);
                Picasso.get().load(imageListTitle.get(position)).into(imageView);
            }
        });
        carouselView.setPageCount(llvpics.size());
    }

    private void declare() {
        rView = (RecyclerView) view.findViewById(R.id.scoreView);
        scoretxt = view.findViewById(R.id.score);

       // rView.setHasFixedSize(true);
        rvLayout = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(rvLayout);
        carouselView = view.findViewById(R.id.carousel);
        pdLoading = new ProgressDialog(getActivity());
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
    }

    private void call() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URlSCORE)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<ListScore> call = api.getScore("WxdGLZuYSNjotTDv", "jI8rjjZFjYcCttVq186M8AK0o3oSW2og");

        call.enqueue(new Callback<ListScore>() {
            @Override
            public void onResponse(Call<ListScore> call, Response<ListScore> response) {

                ListScore dataList = response.body();
                List<ListScore.Data.Matches> soccerlist = dataList.getData().getMatch();
                score = new ArrayList<>();

                for (int i = 0; i < soccerlist.size(); i++) {
                    score.add(new ListScore.Data.Matches(
                            soccerlist.get(i).getStatus()
                            , soccerlist.get(i).getScore()
                            , soccerlist.get(i).getAwayName()
                            , soccerlist.get(i).getAdded()
                            , soccerlist.get(i).getHomeName()
                            , soccerlist.get(i).getLocation()));
                } showPics();

                rvAdapter = new ScoreAdapter(getActivity(), score);
                rView.setAdapter(rvAdapter);
                pdLoading.dismiss();
            }

            @Override
            public void onFailure(Call<ListScore> call, Throwable t) {

            }
        });
    }

    public void carView() {
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

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];


                llp = new ArrayList<>();

                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getTitle();
                    llp.add(new ListVideo(heroList.get(i).getTitle()
                                        , heroList.get(i).getEmbed()
                                        , heroList.get(i).getDate()
                                        , heroList.get(i).getThumbnail()));
                    addItem(heroList.get(i).getThumbnail());
                }

                rvAdapter = new CarouselAdapter(getActivity(), llp);
                rView.setAdapter(rvAdapter);
                pdLoading.dismiss();

            }

            @Override
            public void onFailure(Call<List<ListVideo>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addItem(String pics){
        llvpics.add(pics);
    }
}