package com.afs.restapi.repository;

import com.afs.restapi.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TodoRepositoryInMongo extends MongoRepository<Todo, String> {
//    Optional<Todo> findById(String id);
}
