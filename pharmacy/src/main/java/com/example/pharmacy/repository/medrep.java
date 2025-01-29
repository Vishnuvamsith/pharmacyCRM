package com.example.pharmacy.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pharmacy.entity.medicine;
import java.util.List;
import java.util.Map;
import java.sql.Date;


@Repository
public interface medrep extends MongoRepository<medicine,ObjectId>{
   medicine getByName(String name);
   medicine findById(String id);
   void deleteById(String id);
   List<medicine> findByExpiDate(Date expiDate);
    boolean existsByIdAndExpiDateAndSupplierId(String id, java.util.Date expiDate,int supplierId);
    medicine getByIdAndExpiDateAndSupplierId(String tempId, java.util.Date tempdate, int supplierId);
    medicine getByIdAndExpiDate(String id,java.util.Date expiDate);

}
