package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class TeamModel {
    private int id;
    private String name;
    private String coach;
    private List<PlayerModel> squad = new ArrayList<>();
    private List<MatchesModel> matches = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCoach() {
        return coach;
    }
    
    public void setCoach(String coach) {
        this.coach = coach;
    }
    
    public List<PlayerModel> getSquad() {
        return squad;
    }
    
    public void setSquad(List<PlayerModel> squad) {
        this.squad = squad;
    }

    public List<MatchesModel> getMatches() {
        return matches;
    }
    
    public void setMatches(List<MatchesModel> matches) {
        this.matches = matches;
    }
}

