package uy.com.sofka.Repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import uy.com.sofka.Models.PlayerModel;

public interface PlayerRepository extends ReactiveMongoRepository<PlayerModel, String> {
    
}
