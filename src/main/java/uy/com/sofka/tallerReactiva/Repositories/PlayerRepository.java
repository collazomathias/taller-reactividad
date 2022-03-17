package uy.com.sofka.tallerReactiva.Repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import uy.com.sofka.tallerReactiva.Models.PlayerModel;

public interface PlayerRepository extends ReactiveMongoRepository<PlayerModel, String> {}
