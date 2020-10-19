package com.devdre.football;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.R;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private Context context;
    private List<ListScore.Data.Matches> lsm;
    ListScore.Data.Matches sm;

    public ScoreAdapter(Context context, List lsm) {
        this.context = context;
        this.lsm = lsm;
    }

    @Override
    public ScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragmentscore, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(lsm.get(position));

        sm = lsm.get(position);

        holder.homeTeam.setText(sm.getHomeName());
        holder.awayTeam.setText(sm.getAwayName());
        holder.date.setText(sm.getAdded());
        holder.score.setText(sm.getScore());
        holder.location.setText(sm.getLocation());
        holder.status.setText(sm.getStatus());


    }

    @Override
    public int getItemCount() {
        return lsm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView homeTeam,awayTeam,date,score,location,status;

        public ViewHolder(View itemView) {
            super(itemView);

            homeTeam = (TextView) itemView.findViewById(R.id.home);
            awayTeam = (TextView) itemView.findViewById(R.id.away);
            date = itemView.findViewById(R.id.date);
            score = itemView.findViewById(R.id.score);
            location = itemView.findViewById(R.id.location);
            status = itemView.findViewById(R.id.status);
        }
    }
}
