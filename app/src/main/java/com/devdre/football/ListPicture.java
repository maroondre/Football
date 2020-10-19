package com.devdre.football;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPicture {

    @SerializedName("teams")

    private List<ListPicture.Picture> teams = null;

        public static class Picture
        {
            @SerializedName("strAlternate")
            private String strAlternate;

            @SerializedName("strTeamBadge")
            private String strTeamBadge;

            @SerializedName("strTeamJersey")
            private String strTeamJersey;

            @SerializedName("strTeamFanart1")
            private String strTeamFanart1;

            @SerializedName("strTeamFanart2")
            private String strTeamFanart2;

            @SerializedName("strTeamFanart3")
            private String strTeamFanart3;

            @SerializedName("strTeamFanart4")
            private String strTeamFanart4;

            public Picture(String strAlternate, String strTeamBadge, String strTeamJersey, String strTeamFanart1, String strTeamFanart2, String strTeamFanart3, String strTeamFanart4){
                super();
                this.strAlternate = strAlternate;
                this.strTeamBadge = strTeamBadge;
                this.strTeamJersey = strTeamJersey;
                this.strTeamFanart1 = strTeamFanart1;
                this.strTeamFanart2 = strTeamFanart2;
                this.strTeamFanart3 = strTeamFanart3;
                this.strTeamFanart4 = strTeamFanart4;
            }

            public String getStrAlternate() {
                return strAlternate;
            }

            public void setStrAlternate(String strAlternate) {
                this.strAlternate = strAlternate;
            }

            public String getStrTeamBadge() {
                return strTeamBadge;
            }

            public void setStrTeamBadge(String strTeamBadge) {
                this.strTeamBadge = strTeamBadge;
            }

            public String getStrTeamJersey() {
                return strTeamJersey;
            }

            public void setStrTeamJersey(String strTeamJersey) {
                this.strTeamJersey = strTeamJersey;
            }

            public String getStrTeamFanart1() {
                return strTeamFanart1;
            }

            public void setStrTeamFanart1(String strTeamFanart1) {
                this.strTeamFanart1 = strTeamFanart1;
            }

            public String getStrTeamFanart2() {
                return strTeamFanart2;
            }

            public void setStrTeamFanart2(String strTeamFanart2) {
                this.strTeamFanart2 = strTeamFanart2;
            }

            public String getStrTeamFanart3() {
                return strTeamFanart3;
            }

            public void setStrTeamFanart3(String strTeamFanart3) {
                this.strTeamFanart3 = strTeamFanart3;
            }

            public String getStrTeamFanart4() {
                return strTeamFanart4;
            }

            public void setStrTeamFanart4(String strTeamFanart4) {
                this.strTeamFanart4 = strTeamFanart4;
            }
        }

    public List<ListPicture.Picture> getTeams() {
        return teams;
    }

    public void setTeams(List<ListPicture.Picture> teams) {
        this.teams = teams;
    }
}

