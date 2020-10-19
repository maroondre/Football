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
import android.view.MenuItem;
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

public class SocialMedia extends AppCompatActivity {

    private Button btnweb;
    private TextView tFb,tTwi,tIg,tYt;
    private ImageView image;
    private ListView listView;
    private RecyclerView sView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayout;
    private List<ListSoccer.Team> tsoc;
    private ProgressDialog pdLoading;
    private BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        getSupportActionBar().setTitle("Team Social Media Accounts");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        declare();
        navBottom();
        call();
    }

    public void declare()
    {
        tFb = (TextView) findViewById(R.id.txtFb);
        tTwi = (TextView) findViewById(R.id.txtTwitter);
        tIg = (TextView) findViewById(R.id.txtIG);
        tYt = (TextView) findViewById(R.id.txtYt);
        sView = (RecyclerView) findViewById(R.id.sView);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.socialmedia);
       // bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();

      //  sView.setHasFixedSize(true);
        rvLayout = new LinearLayoutManager(this);
        sView.setLayoutManager(rvLayout);

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
                    case R.id.teams:
                        Intent team = new Intent(getApplicationContext(), Team.class);
                        startActivity(team);
                        overridePendingTransition(0,0);
                        break;
                    /*case R.id.video:
                        Intent vid = new Intent(getApplicationContext(), Video.class);
                        startActivity(vid);
                        overridePendingTransition(0,0);
                        break;*/
                    case R.id.socialmedia:
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
                rvAdapter =  new SocialAdapter(SocialMedia.this ,tsoc);
                sView.setAdapter(rvAdapter);
                pdLoading.dismiss();
            };

            @Override
            public void onFailure(Call<ListSoccer> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}