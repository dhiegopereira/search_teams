package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.models.TeamModel;
import com.example.models.MatchesModel;
import com.example.models.PlayerModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SoccerController {    
    private static final String API_KEY = "YOUR_TOKEN_HERE";
    public List<MatchesModel> getMatchesForTeam(int teamId) throws JsonMappingException, JsonProcessingException {
        String matchesUrl = "http://api.football-data.org/v4/teams/" + teamId + "/matches";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(
                matchesUrl,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        JsonNode matchesNode = jsonNode.get("matches");

        List<MatchesModel> matchesModelList = new ArrayList<>();

       
        if (matchesNode != null && matchesNode.isArray()) {
            for (JsonNode matcheNode : matchesNode) {
                String competitionName = matcheNode.get("competition").get("name").asText();
                String utcDate = matcheNode.get("utcDate").asText();
                String homeTeam = matcheNode.get("homeTeam").get("name").asText();
                String awayTeam = matcheNode.get("awayTeam").get("name").asText();
                String winner = matcheNode.get("score").get("winner").asText();
                int scoreHome = matcheNode.get("score").get("fullTime").get("home").asInt();
                int scoreAway = matcheNode.get("score").get("fullTime").get("away").asInt();
            

                MatchesModel matchesModel = new MatchesModel();
                matchesModel.setCompetitionName(competitionName);
                matchesModel.setUtcDate(utcDate);
                matchesModel.setHomeTeamName(homeTeam);
                matchesModel.setAwayTeamName(awayTeam);
                matchesModel.setWinner(winner);
                matchesModel.setScoreHome(scoreHome);
                matchesModel.setScoreAway(scoreAway);

                matchesModelList.add(matchesModel);

            }
            
            return matchesModelList;
        }
        return null;
    }

    public TeamModel searchTeam(String teamName) throws JsonMappingException, JsonProcessingException {
        String teamsUrl = "http://api.football-data.org/v4/competitions/BSA/teams";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response = restTemplate.exchange(
            teamsUrl,
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        JsonNode teamsNode = jsonNode.get("teams");

        if (teamsNode != null && teamsNode.isArray()) {
            for (JsonNode teamNode : teamsNode) {
                String name = teamNode.get("name").asText();

                if (name.toLowerCase().contains(teamName.toLowerCase())) {
                    TeamModel teamModel = new TeamModel();

                    int id = teamNode.get("id").asInt();

                    teamModel.setMatches(getMatchesForTeam(id));

                    String coachName = teamNode.get("coach").get("name").asText();

                    teamModel.setId(id);
                    teamModel.setName(name);
                    teamModel.setCoach(coachName);

                    JsonNode squadNode = teamNode.get("squad");
                    if (squadNode != null && squadNode.isArray()) {
                        for (JsonNode playerNode : squadNode) {
                            String playerName = playerNode.get("name").asText();
                            String playerPosition = playerNode.get("position").asText();
                           
                            PlayerModel playerModel = new PlayerModel();
                            playerModel.setName(playerName);
                            playerModel.setPosition(playerPosition);

                            teamModel.getSquad().add(playerModel);
                        }
                    }
                    return teamModel;
                }
            }
        }

        return null;
    }
}
