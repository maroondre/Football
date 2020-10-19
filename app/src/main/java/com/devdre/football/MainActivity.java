package com.devdre.football;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private  Button btnweb;
    private TextView title,swipe;
    private ImageView image;
    private ListView listView;
    private RecyclerView rView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;
    private List<ListSoccer.Team> soc;
    private ProgressDialog pdLoading;
    private BottomNavigationView bottomNavigationView;
    private CountDownTimer count;
    Connection connection;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Football Stadium");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        connection = new Connection();
        if(connection.isConnected(this)) {
            declare();
            navBottom();
            call();
            blink();
        }
    }

    private void blink() {

        count = new CountDownTimer(7000, 1000) {
            @SuppressLint("RestrictedApi")
            @Override
            public void onTick(long millisUntilFinished) {
                if (swipe.getVisibility() == View.VISIBLE) {
                    swipe.setVisibility(View.INVISIBLE);
                } else {
                    swipe.setVisibility(View.VISIBLE);
                }
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onFinish() {
                swipe.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    public void declare()
    {
        title = (TextView) findViewById(R.id.txtview);
        image = (ImageView) findViewById(R.id.imgView);
        rView = (RecyclerView) findViewById(R.id.rView);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        swipe = (TextView) findViewById(R.id.swipe);
        //bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
       // rView.setHasFixedSize(true);
        rvLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rView.setLayoutManager(rvLayout);
        bottomNavigationView.setSelectedItemId(R.id.stadium);
        // load the store fragment by default

    }

    private void navBottom()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    /*case R.id.video:
                        Intent stad =  new Intent(getApplicationContext(), Video.class);
                        startActivity(stad);
                        overridePendingTransition(0,0);
                        break;*/
                    case R.id.teams:
                        Intent team = new Intent(getApplicationContext(), Team.class);
                        startActivity(team);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.socialmedia:
                        Intent soc = new Intent(getApplicationContext(), SocialMedia.class);
                        startActivity(soc);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.stadium:
                        return true;
                }
                return false;
            }
        });
    }


    private void call()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<ListSoccer> call = api.getSoccer();

        call.enqueue(new Callback<ListSoccer>() {
            @Override
            public void onResponse(Call<ListSoccer> call, Response<ListSoccer> response) {
                ListSoccer dataList = response.body(); // Calling the List class (Getters and Setters)

                List<ListSoccer.Team> soccerlist = dataList.getTeams(); // Calling the subclass List(Array String)
                soc = new ArrayList<>();

                for(int i = 0; i < soccerlist.size(); i++) {
                    soc.add( new ListSoccer.Team(
                            soccerlist.get(i).getStrName()
                            ,  soccerlist.get(i).getStrAlternate()
                            , soccerlist.get(i).getStrLeague()
                            , soccerlist.get(i).getStrWebsite()
                            , soccerlist.get(i).getStrStadiumThumb()
                            , soccerlist.get(i).strDescriptionEN()
                            , soccerlist.get(i).getStrStadiumDescription()
                            , soccerlist.get(i).getStrTeamLogo()
                            , soccerlist.get(i).getStrFacebook()
                            , soccerlist.get(i).getStrTwitter()
                            , soccerlist.get(i).getStrInstagram()
                            , soccerlist.get(i).getStrYoutube()
                            , soccerlist.get(i).getStrTeamBadge()));
                         //String strAlternate, String strLeague,  String strRSS, String strStadiumThumb, String strStadiumDescription
                };

                //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), simple_list_item_1, nba));
                rvAdapter =  new AdapterSoccer(MainActivity.this ,soc);
                rView.setAdapter(rvAdapter);
                SnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(rView);
                pdLoading.dismiss();
            };

            @Override
            public void onFailure(Call<ListSoccer> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}