package com.example.pharmacy.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pharmacy.entity.patient;


public interface patientrepo extends MongoRepository<patient,ObjectId>{

    patient findByName(String name);

    void deleteByName(String name);
}
