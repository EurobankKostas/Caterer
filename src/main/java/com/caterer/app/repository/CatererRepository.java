package com.caterer.app.repository;

import com.caterer.app.entity.Caterer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatererRepository extends MongoRepository<Caterer, Long> {

    Optional<List<Caterer>> findByItemId(String itemId);
    Optional<Caterer> findByName(String name);
}
