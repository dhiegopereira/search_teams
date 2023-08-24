package com.example.models;

public class MatchesModel {
    private String competitionName;
    private String utcDate;
    private String homeTeamName;
    private String awayTeamName;
    private String winner;
    private int scoreHome;
    private int scoreAway;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getUtcDate() {
        return utcDate;
    }
    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }
    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }
    
    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }
}
