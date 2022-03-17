package com.example.demo.Repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.Models.PlayerModel;

public interface PlayerRepository extends ReactiveMongoRepository<PlayerModel, String> {}
