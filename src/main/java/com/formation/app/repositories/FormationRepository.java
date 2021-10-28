package com.formation.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.formation.app.entities.Formation;

@Repository
public interface FormationRepository extends MongoRepository<Formation, String> {
	
}
