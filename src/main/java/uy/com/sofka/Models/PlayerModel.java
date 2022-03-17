package uy.com.sofka.Models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="players")
public class PlayerModel {
    
    @Id
    private Long id;
    public String name;
    public Integer age;
    public String icon;
    public String national;
    public Integer winners;
    public Integer gamers;
    public Double ranking;
    public String club;

    public PlayerModel(Long playerId, String playerName, Integer playerAge, String playerIcon, String playerNationality, Integer playerWinners, Integer playerGamers, Double playerRanking, String playerClub) {
        this.id = playerId;
        this.name = playerName;
        this.age = playerAge;
        this.icon = playerIcon;
        this.national = playerNationality;
        this.winners = playerWinners;
        this.gamers = playerGamers;
        this.ranking = playerRanking;
        this.club = playerClub;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getIcon() {
        return icon;
    }

    public String getNational() {
        return national;
    }

    public Integer getWinners() {
        return winners;
    }

    public Integer getGamers() {
        return gamers;
    }

    public Double getRanking() {
        return ranking;
    }

    public String getClub() {
        return club;
    }

}
