package com.devdre.football.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.ListModels.ListVideo;
import com.devdre.football.R;
import com.synnapps.carouselview.CarouselView;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder>{


    private Context context;
    private List<ListVideo> llv;
    ListVideo lv;
    public CarouselAdapter(Context context, List llv) {
        this.context = context;
        this.llv = llv;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragmentscore, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    //removing the html before the url code
    private String removeUrl(String all){
        String s = "src='";
        int ix = all.indexOf(s)+s.length();
        String changedURL =  all.substring(ix, all.indexOf("'", ix+1));
        changedURL = "<div style='width:100%;height:0px;position:relative;padding-bottom:56.250%;'><iframe src='" +
                changedURL +
                "' frameborder='0' width='100%' height='100%' allowfullscreen allow='autoplay; fullscreen' style='width:100%;height:120%;position:absolute;left:0px;top:0px;overflow:hidden;'></iframe></div>";
        return changedURL;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(llv.get(position));
        lv = llv.get(position);

    }

    @Override
    public int getItemCount() {
        return llv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CarouselView carouselView;
        public ViewHolder(View itemView) {
            super(itemView);

            carouselView = itemView.findViewById(R.id.carousel);



        }
    }
}
