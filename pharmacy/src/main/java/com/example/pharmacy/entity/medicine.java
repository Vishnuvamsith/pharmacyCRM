package com.example.pharmacy.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection ="medicines")
@Data @AllArgsConstructor @NoArgsConstructor
public class medicine {
    @Id
    private ObjectId _id;
    @Indexed(unique = true) 
    private String id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private int supplierId;
    @CreatedDate
    private Date created_at;
    private Date expiDate;
}
