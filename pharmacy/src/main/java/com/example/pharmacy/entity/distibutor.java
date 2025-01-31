package com.example.pharmacy.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class distibutor {
    @Id
    private ObjectId _id;
    private String name;
    private int supplierId;
    private String item;
    private int quantity;
    @CreatedDate
    private Date purchasedDate;
    private int itemSold;
    private int remaining;
}
