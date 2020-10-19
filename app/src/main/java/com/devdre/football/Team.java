package com.devdre.football;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Team extends AppCompatActivity {

    private Button btnweb;
    private TextView title,desc;
    private ImageView image;
    private ListView listView;
    private RecyclerView rView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;
    private List<ListSoccer.Team> tsoc;
    private ProgressDialog pdLoading;
    private BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;
    private FloatingActionButton fab;
    private CountDownTimer count;
    Connection connection;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Football Teams");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_team);
        connection = new Connection();
        if(connection.isConnected(this)) {
            declare();
            navBottom();
            call();
            blink();
        }
    }

    public void declare()
    {
        title = (TextView) findViewById(R.id.teamTitle);
        desc = (TextView) findViewById(R.id.teamDesc);
        rView = (RecyclerView) findViewById(R.id.tView);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.teams);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
      //  rView.setHasFixedSize(true);
        rvLayout = new LinearLayoutManager(this);
        rView.setLayoutManager(rvLayout);

    }

    private void navBottom()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.stadium:
                        Intent stad =  new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(stad);
                        overridePendingTransition(0,0);
                        break;
                    /*case R.id.video:
                        Intent vid = new Intent(getApplicationContext(), Video.class);
                        startActivity(vid);
                        overridePendingTransition(0,0);
                        break;*/
                    case R.id.socialmedia:
                        Intent soc = new Intent(getApplicationContext(), SocialMedia.class);
                        startActivity(soc);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.teams:
                        return true;
                }
                return true;
            }
        });
    }

    public void call()
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
                ListSoccer dataList = response.body();

                List<ListSoccer.Team> soccerlist = dataList.getTeams();
                tsoc = new ArrayList<>();

                for(int i = 0; i<soccerlist.size(); i++) {
                    tsoc.add( new ListSoccer.Team(
                            soccerlist.get(i).getStrName()
                            , soccerlist.get(i).getStrAlternate()
                            , soccerlist.get(i).getStrLeague()
                            , soccerlist.get(i).getStrWebsite()
                            , soccerlist.get(i).getStrStadiumThumb()
                            , soccerlist.get(i).getStrStadiumDescription()
                            , soccerlist.get(i).strDescriptionEN()
                            , soccerlist.get(i).getStrTeamLogo()
                            , soccerlist.get(i).getStrFacebook()
                            , soccerlist.get(i).getStrTwitter()
                            , soccerlist.get(i).getStrInstagram()
                            , soccerlist.get(i).getStrYoutube()
                            , soccerlist.get(i).getStrTeamBadge()));

                };

                //listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), simple_list_item_1, nba));
                rvAdapter =  new TeamAdapter(Team.this ,tsoc);
                rView.setAdapter(rvAdapter);
                pdLoading.dismiss();
            };

            @Override
            public void onFailure(Call<ListSoccer> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void blink() {

        count = new CountDownTimer(10000, 1000) {
            @SuppressLint("RestrictedApi")
            @Override
            public void onTick(long millisUntilFinished) {
                if (fab.getVisibility() == View.VISIBLE) {
                    fab.setVisibility(View.INVISIBLE);
                } else {
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onFinish() {
                fab.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

//        final Handler handler = new Handler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final int timeToBlink = 900;  //in milissegunds
//                final int count = 0;
//                try{
//                    Thread.sleep(timeToBlink);
//                }catch (Exception e) {}
//                handler.post(new Runnable() {
//                    @SuppressLint("RestrictedApi")
//                    @Override
//                    public void run() {
//
//                        if(fab.getVisibility() == View.VISIBLE){
//                            fab.setVisibility(View.INVISIBLE);
//                        }else{
//                            fab.setVisibility(View.VISIBLE);
//
//                        }
//
//                        blink();
//                    }
//                });
//            }
//        }).start();


}