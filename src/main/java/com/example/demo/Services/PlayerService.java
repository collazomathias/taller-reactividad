package com.example.demo.Services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import com.example.demo.Models.PlayerModel;
import com.example.demo.Repositories.PlayerRepository;

@Service
public class PlayerService {
    
    @Autowired
    PlayerRepository playerRepository;

    public Flux<PlayerModel> getPlayers() {
        return playerRepository.findAll()
            .buffer()
            .flatMap(player -> Flux.fromStream(player.parallelStream()));
    }

    public Flux<PlayerModel> getPlayersByClub(String club) {
        return playerRepository.findAll()
            .buffer()
            .flatMap(player -> Flux.fromStream(player.parallelStream()))
            .filter(player -> player.getClub().equals(club));
    }

    public Flux<PlayerModel> getPlayersByNationality(String nationality) {
        return playerRepository.findAll()
            .buffer()
            .flatMap(player -> Flux.fromStream(player.parallelStream()))
            .filter(player -> player.getNational().equals(nationality));
    }

    public Flux<PlayerModel> getPlayersByAge(Integer age) {
        return playerRepository.findAll()
            .buffer()
            .flatMap(player -> Flux.fromStream(player.parallelStream()))
            .filter(player -> player.getAge() >= age);
    }

    public Flux<List<PlayerModel>> getRankingPlayersByNationality() {
        return playerRepository.findAll()
            .buffer()
            .flatMap(player -> Flux.fromStream(player.parallelStream()))
            .groupBy(PlayerModel::getNational)
            .flatMap(Flux::collectList)
            .map(ranking -> {
                ranking.sort(Comparator.comparingDouble(PlayerModel::getRanking));
                return ranking;
            });
    }

}
