package com.devdre.football.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.devdre.football.ListModels.ListVideo;
import com.devdre.football.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{


    private Context context;
    private List<ListVideo> llv;
    ListVideo lv;
    String vid;
    int[] imagelist;

    public VideoAdapter(Context context, List llv) {
        this.context = context;
        this.llv = llv;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.videoview, parent, false);
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
        holder.vName.setText(lv.getTitle());
        holder.date.setText(lv.getDate());
        vid = lv.getEmbed();
        holder.videoView.getSettings().setJavaScriptEnabled(true);
        holder.videoView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        holder.videoView.loadData(removeUrl(vid),"text/html", "UTF-8");

    }

    @Override
    public int getItemCount() {
        return llv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView vName,date;
        public TextView pJobProfile;
        public WebView videoView;
        public ViewHolder(View itemView) {
            super(itemView);

            vName = (TextView) itemView.findViewById(R.id.vtxtview);
            videoView = (WebView) itemView.findViewById(R.id.webView);
            date = itemView.findViewById(R.id.dateVid);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ListVideo cpu = (ListVideo) view.getTag();
//
//                    //Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }
    }

}
