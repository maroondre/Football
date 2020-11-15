package com.devdre.football.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.ListModels.ListSoccer;
import com.devdre.football.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder>{

    private Context context;
    private List<ListSoccer.Team> lls;
    ListSoccer.Team ls;

    public SocialAdapter(Context context, List lls) {
        this.context = context;
        this.lls = lls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.socialmedia, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(lls.get(position));

        ls = lls.get(position);

        //holder.socFb.setWebViewClient(new WebViewClient());
        //holder.socFb.loadUrl(ls.getStrFacebook());
        Picasso.get().load(lls.get(position).getStrTeamBadge()).into(holder.img);

        holder.socFb.setText(ls.getStrFacebook());
//        holder.socFb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ls.getStrFacebook()));
//                context.startActivity(browserIntent);
//            }
//        });

        holder.socTwi.setText(ls.getStrTwitter());
//        holder.socTwi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ls.getStrTwitter()));
//                context.startActivity(browserIntent);
//            }
//        });

        holder.socIg.setText(ls.getStrInstagram());
//        holder.socIg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ls.getStrInstagram()));
//                context.startActivity(browserIntent);
//            }
//        });

        holder.socYt.setText(ls.getStrYoutube());
//        holder.socYt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ls.getStrYoutube()));
//                context.startActivity(browserIntent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return lls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView socFb, socTwi, socIg, socYt;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            socFb = (TextView) itemView.findViewById(R.id.txtFb);
            socTwi = (TextView) itemView.findViewById(R.id.txtTwitter);
            socIg = (TextView) itemView.findViewById(R.id.txtIG);
            socYt = (TextView) itemView.findViewById(R.id.txtYt);
            img = (ImageView) itemView.findViewById(R.id.smImg);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ListSoccer cpu = (ListSoccer) view.getTag();
//
//                    //Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }
    }
    
}
