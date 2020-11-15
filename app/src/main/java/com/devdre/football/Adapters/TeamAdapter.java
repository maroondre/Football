package com.devdre.football.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.ListModels.ListSoccer;
import com.devdre.football.R;
import com.devdre.football.Fragments.TeamWebview;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    public static String teamWeb;
    private Context context;
    private List<ListSoccer.Team> lls;
    ListSoccer.Team ls;

    public TeamAdapter(Context context, List lls) {
        this.context = context;
        this.lls = lls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(lls.get(position));
        ls = lls.get(position);

        holder.teamTitle.setText(ls.getStrAlternate());
        holder.teamDesc.setText(ls.strDescriptionEN());
        Picasso.get().load(lls.get(position).getStrTeamLogo()).into(holder.teamImg);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0 ; i<lls.size();i++ ){
                    if(holder.teamTitle.getText() == lls.get(i).getStrAlternate()){
                        teamWeb = lls.get(i).getStrWebsite().toString();
                        break;
                    }

                }
                Intent web = new Intent(v.getContext(), TeamWebview.class);
               web.putExtra("link", teamWeb);
               context.startActivity(web);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView teamTitle,more;
        public TextView teamDesc;
        public ImageView teamImg;

        public ViewHolder(View itemView) {
            super(itemView);

            teamTitle = (TextView) itemView.findViewById(R.id.teamTitle);
            teamDesc = (TextView) itemView.findViewById(R.id.teamDesc);
            teamImg = (ImageView) itemView.findViewById(R.id.teamImage);
            more = (TextView) itemView.findViewById(R.id.More);

         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent web = new Intent(view.getContext(),TeamWebview.class);
                    web.putExtra()
                    view.getContext().startActivity(web);
                    //ListSoccer cpu = (ListSoccer) view.getTag();

                    //Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });*/

        }
    }

}
