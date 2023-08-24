package com.example;

import com.example.controllers.SoccerController;
import com.example.models.TeamModel;
import com.example.models.PlayerModel;
import com.example.models.MatchesModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        SoccerController soccerController = new SoccerController();

        while (true) {
            System.out.print("Enter the name of the team (or press Enter to exit): ");
            String searchTeam = scanner.nextLine().trim();
            
            if (searchTeam.isEmpty()) {
                System.out.println("Exiting the program.");
                break;
            }

            TeamModel team = soccerController.searchTeam(searchTeam);

            if (team != null) {
                System.out.println("Team: " + team.getName());
                System.out.println("Coach: " + team.getCoach());
                System.out.println("Squad Players:");
                for (PlayerModel player : team.getSquad()) {
                    System.out.println("- " + player.getName() + " (" + player.getPosition() + ")");
                }
                
                System.out.println("Recent Matches:");
                List<MatchesModel> matchesList = team.getMatches();
                if (matchesList != null && !matchesList.isEmpty()) {
                    for (MatchesModel match : matchesList) {
                        System.out.println("Competition: " + match.getCompetitionName());
                        System.out.println("Date: " + match.getUtcDate());
                        System.out.println("Home Team: " + match.getHomeTeamName());
                        System.out.println("Away Team: " + match.getAwayTeamName());
                        System.out.println("Winner: " + match.getWinner());
                        System.out.println("Score: " + match.getScoreHome() + " x " + match.getScoreAway());
                        System.out.println();
                    }
                } else {
                    System.out.println("No match information available.");
                }
            } else {
                System.out.println("Team not found.");
            }
        }
        
        scanner.close();
    }
}

