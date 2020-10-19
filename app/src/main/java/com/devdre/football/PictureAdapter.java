package com.devdre.football;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {


    private Context context;
    private List<ListPicture.Picture> llp;
    ListPicture.Picture lp;

    public PictureAdapter(Context context, List llp) {
        this.context = context;
        this.llp = llp;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fan_art, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(llp.get(position));
        lp = llp.get(position);


    }

    @Override
    public int getItemCount() {
        return llp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView teamTitle;
        public TextView teamDesc;
        public ImageView img1,img2, img3, img4, img5, img6;

        public ViewHolder(View itemView) {
            super(itemView);

            img1 = (ImageView) itemView.findViewById(R.id.img1);
            img2 = (ImageView) itemView.findViewById(R.id.img2);
            img3 = (ImageView) itemView.findViewById(R.id.img3);
            img4 = (ImageView) itemView.findViewById(R.id.img4);
            img5 = (ImageView) itemView.findViewById(R.id.img5);
            img6 = (ImageView) itemView.findViewById(R.id.img6);

        }
    }


}
