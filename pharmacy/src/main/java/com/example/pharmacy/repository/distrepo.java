package com.example.pharmacy.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmacy.entity.distibutor;
import java.util.List;

@Repository
public interface distrepo extends MongoRepository<distibutor,ObjectId>{
    distibutor findBySupplierId(int supplierId);
}
