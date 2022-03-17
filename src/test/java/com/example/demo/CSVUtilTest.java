package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.Models.Player;

public class CSVUtilTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void stream_filtrarJugadoresMayoresA35(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, List<Player>> listFilter = list.parallelStream()
                .filter(player -> player.age >= 35)
                .map(player -> {
                    player.name = player.name.toUpperCase(Locale.ROOT);
                    return player;
                })
                .flatMap(playerA -> list.parallelStream()
                        .filter(playerB -> playerA.club.equals(playerB.club))
                )
                .distinct()
                .collect(Collectors.groupingBy(Player::getClub));

        assert listFilter.size() == 322;
    }

    @Test
    void reactive_filtrarJugadoresMayoresA35(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .filter(player -> player.age >= 35)
                .map(player -> {
                    player.name = player.name.toUpperCase(Locale.ROOT);
                    return player;
                })
                .buffer(100)
                .flatMap(playerA -> listFlux
                         .filter(playerB -> playerA.stream()
                                 .anyMatch(a ->  a.club.equals(playerB.club)))
                )
                .distinct()
                .collectMultimap(Player::getClub);

        assert listFilter.block().size() == 322;
    }


    @Test
    void reactive_filtrarJugadoresPorClubMayoresA34(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .filter(player -> player.age >= 34)
                .filter(player -> player.club.equals("Paris Saint-Germain"))
                .distinct()
                .collectMultimap(Player::getClub);
        
        System.out.println("Equipo: ");
        listFilter.block().forEach((equipo, players) -> {
            System.out.println(equipo);
            players.forEach(player -> {
                System.out.println("[Nombre: " + player.name + "] [" + "Edad: " + player.age + " años]");
                assert player.club.equals("Paris Saint-Germain");
                assert player.age >= 34;
            });
        });
    }


    @Test
    void reactive_filtrarRankingJugadoresPorNacionalidad() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> listFlux = Flux.fromStream(list.parallelStream()).cache();
        Mono<Map<String, Collection<Player>>> listFilter = listFlux
                .distinct()
                .sort((a, player) -> player.winners)
                .collectMultimap(Player::getNational);
        
        System.out.println("Equipo: ");
        listFilter.block().forEach((equipo, players) -> {
            System.out.println(equipo);
            players.forEach(player -> {
                System.out.println("[Nombre: " + player.name + "] [" + "Edad: " + player.age + " años] | [Victorias: " + player.winners + "]");
            });
        });
    }



}
