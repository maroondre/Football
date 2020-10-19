package com.devdre.football;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSoccer extends RecyclerView.Adapter<AdapterSoccer.ViewHolder>{

    private Dialog myDialog;
    private Context context;
    private List<ListSoccer.Team> soccerList;
    ListSoccer.Team soccer;
    public String desc;

    public AdapterSoccer(Context context, List soccerList) {
        this.context = context;
        this.soccerList =  soccerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(getSoccerList().get(position));

         soccer = soccerList.get(position);
        desc = soccer.getStrStadiumDescription().toString();
        holder.pName.setText(soccer.getStrAlternate());
        Picasso.get().load(soccerList.get(position).getStrStadiumThumb()).into(holder.imgView);

        holder.web.setText(soccer.getStrStadiumDescription());
        holder.web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.stadium);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                holder.stad.setText(desc);
                holder.stad.setMovementMethod(new ScrollingMovementMethod());
                myDialog.show();

            }
        });

    }

    private List<ListSoccer.Team> getSoccerList() {
        return soccerList;
    }

    @Override
    public int getItemCount() {
        return soccerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName,web,stad;
        public ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);

            myDialog = new Dialog(itemView.getContext());
            stad = (TextView) myDialog.findViewById(R.id.stadText);
            pName = (TextView) itemView.findViewById(R.id.txtview);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            web = (TextView) itemView.findViewById(R.id.stadDesc);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ListSoccer.Team cpu = (ListSoccer.Team) view.getTag();
//
//                   // Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }
    }


}
