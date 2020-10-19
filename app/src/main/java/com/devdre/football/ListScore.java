package com.devdre.football;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListScore {

    @SerializedName("success")
    public boolean success;

    @SerializedName("data")
    public Data data;

        public static class Data{

            @SerializedName("match")
            private List<Matches> match = null;

                public static class Matches {
                    @SerializedName("status")
                    private String status;

                    @SerializedName("score")
                    private String score;

                    @SerializedName("away_name")
                    private String awayName;

                    @SerializedName("added")
                    private String added;

                    @SerializedName("home_name")
                    private String homeName;

                    @SerializedName("location")
                    private String location;


                    public Matches(String status, String score, String awayName, String added, String homeName,
                                   String location) {
                        super();
                        this.status = status;
                        this.score = score;
                        this.awayName = awayName;
                        this.added = added;
                        this.homeName = homeName;
                        this.location = location;
                    }


                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getScore() {
                        return score;
                    }

                    public void setScore(String score) {
                        this.score = score;
                    }

                    public String getAwayName() {
                        return awayName;
                    }

                    public void setAwayName(String awayName) {
                        this.awayName = awayName;
                    }

                    public String getAdded() {
                        return added;
                    }

                    public void setAdded(String added) {
                        this.added = added;
                    }

                    public String getHomeName() {
                        return homeName;
                    }

                    public void setHomeName(String homeName) {
                        this.homeName = homeName;
                    }

                    public String getLocation() {
                        return location;
                    }

                    public void setLocation(String location) {
                        this.location = location;
                    }

                }

                public List<Matches> getMatch () {
                return match;
            }

                public void setMatch (List < Matches > match) {
                this.match = match;
            }
        }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
