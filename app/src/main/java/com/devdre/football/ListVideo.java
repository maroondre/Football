package com.devdre.football;

import com.google.gson.annotations.SerializedName;

public class ListVideo {

    @SerializedName("title")
    public String title;

    @SerializedName("embed")
    public String embed;

    @SerializedName("thumbnail")
    public String thumbnail;

    @SerializedName("date")
    public String date;

    public ListVideo(String title, String embed, String date, String thumbnail) {
        super();
        this.title = title;
        this.embed = embed;
        this.date = date;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmbed() {
        return embed;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail(){return thumbnail;}

    public void setThumbnail(String thumbnail){this.thumbnail = thumbnail;}

}
