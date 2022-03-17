package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import com.example.demo.Models.PlayerModel;
import com.example.demo.Services.PlayerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PlayerController {
    
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping(value = "/players")
    public Flux<PlayerModel> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping(value = "/playersByAge/{age}")
    public Flux<PlayerModel> getPlayersByAge(@PathVariable("age") Integer age) {
        return playerService.getPlayersByAge(age);
    }

    @GetMapping(value = "/playersByClub/{club}")
    public Flux<PlayerModel> getPlayersByClub(@PathVariable("club") String club) {
        return playerService.getPlayersByClub(club);
    }

    @GetMapping(value = "/playersByNationality/{national}")
    public Flux<PlayerModel> getPlayersByNationality(@PathVariable("national") String national) { 
        return playerService.getPlayersByNationality(national);
    }

    @GetMapping(value = "/rankingPlayersByNationality")
    public Flux<List<PlayerModel>> getRankingPlayersByNationality() {
        return playerService.getRankingPlayersByNationality();
    }

}
