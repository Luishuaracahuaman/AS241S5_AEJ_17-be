package com.luisenrique.api_ia_mongodb.repository;

import com.luisenrique.api_ia_mongodb.model.IaResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IaResponseRepository extends ReactiveMongoRepository<IaResponse, String> {
}