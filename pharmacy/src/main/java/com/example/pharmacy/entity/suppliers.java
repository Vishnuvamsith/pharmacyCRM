package com.example.pharmacy.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class suppliers {
    @Id
    private ObjectId _id; 
    private String name;
    private long phoneNumber;
    private String email;
    private String address;
}
