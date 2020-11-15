package com.devdre.football.ListModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ListSoccer {

    @SerializedName("teams")
    private List<Team> teams = null;

            public static class Team {

                @SerializedName("strTeam")
                private String strName;

                @SerializedName("strAlternate")
                private String strAlternate;

                @SerializedName("strLeague")
                private String strLeague;

                @SerializedName("strWebsite")
                private String strWebsite;

                @SerializedName("strStadiumThumb")
                private String strStadiumThumb;

                @SerializedName("strStadiumDescription")
                private String strStadiumDescription;

                @SerializedName("strDescriptionEN")
                private String strDescriptionEN;

                @SerializedName("strTeamLogo")
                private String strTeamLogo;

                @SerializedName("strFacebook")
                private String strFacebook;

                @SerializedName("strTwitter")
                private String strTwitter;

                @SerializedName("strInstagram")
                private String strInstagram;

                @SerializedName("strYoutube")
                private String strYoutube;

                @SerializedName("strTeamBadge")
                private String strTeamBadge;

                public Team(String strName, String strAlternate, String strLeague,  String strWebsite, String strStadiumThumb, String strStadiumDescription, String strDescriptionEN, String strTeamLogo,
                             String strFacebook, String strTwitter,  String strInstagram, String strYoutube, String strTeamBadge) {
                    super();

                    this.strName = strName;
                    this.strAlternate = strAlternate;
                    this.strLeague = strLeague;
                    this.strWebsite = strWebsite;
                    this.strStadiumThumb = strStadiumThumb;
                    this.strStadiumDescription = strStadiumDescription;
                    this.strDescriptionEN = strDescriptionEN;
                    this.strTeamLogo = strTeamLogo;
                    this.strFacebook = strFacebook;
                    this.strTwitter = strTwitter;
                    this.strInstagram = strInstagram;
                    this.strYoutube = strYoutube;
                    this.strTeamBadge =strTeamBadge;

                }

                public String getStrName() {
                    return strName;
                }

                public void setStrName(String strName) {
                    this.strName = strName;
                }

                public String getStrAlternate() {
                    return strAlternate;
                }

                public void setStrAlternate(String strAlternate) {
                    this.strAlternate = strAlternate;
                }

                public String getStrLeague() {
                    return strLeague;
                }

                public void setStrLeague(String strLeague) {
                    this.strLeague = strLeague;
                }

                public String getStrWebsite() {
                    return strWebsite;
                }

                public void setStrWebsite(String strWebsite) {
                    this.strWebsite = strWebsite;
                }

                public String getStrStadiumThumb() {
                    return strStadiumThumb;
                }

                public void setStrStadiumThumb(String strStadiumThumb) {
                    this.strStadiumThumb = strStadiumThumb;
                }

                public String getStrStadiumDescription() {
                    return strStadiumDescription;
                }

                public void setStrStadiumDescription(String strStadiumDescription) {
                    this.strStadiumDescription = strStadiumDescription;
                }

                public String strDescriptionEN() {
                    return strDescriptionEN;
                }

                public void strDescriptionEN(String strDescriptionEN) {
                    this.strDescriptionEN = strDescriptionEN;
                }

                public String getStrTeamLogo() { return strTeamLogo;}

                public void setStrTeamLogo(String strTeamLogo) {this.strTeamLogo = strTeamLogo;}

                public String getStrFacebook() {
                    return strFacebook;
                }

                public void setStrFacebook(String strFacebook) {
                    this.strFacebook = strFacebook;
                }

                public String getStrTwitter() {
                    return strTwitter;
                }

                public void setStrTwitter(String strTwitter) {
                    this.strTwitter = strTwitter;
                }

                public String getStrInstagram() {
                    return strInstagram;
                }

                public void setStrInstagram(String strInstagram) {
                    this.strInstagram = strInstagram;
                }

                public String getStrYoutube() {
                    return strYoutube;
                }

                public void setStrYoutube(String strYoutube) {
                    this.strYoutube = strYoutube;
                }

                public String getStrTeamBadge() {
                    return strTeamBadge;
                }

                public void setStrTeamBadge(String strTeamBadge) {
                    this.strTeamBadge = strTeamBadge;
                }
            }

        public List<Team> getTeams() {
            return teams;
        }

        public void setTeams(List<Team> teams) {
            this.teams = teams;
        }

    }
