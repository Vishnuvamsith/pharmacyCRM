package com.example.pharmacy.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pharmacy.entity.suppliers;

public interface supprepo extends MongoRepository<suppliers,ObjectId>{

    suppliers findByName(String name);

    void deleteByName(String name);
    
}
