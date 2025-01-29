package com.example.pharmacy.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class medinfo {
    private String id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private int supplierId;
    private Date expiDate;
}
