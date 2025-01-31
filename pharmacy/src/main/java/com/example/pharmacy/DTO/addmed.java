package com.example.pharmacy.DTO;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class addmed {
    private String id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private int supplier_id;
    private Date expiDate;
}
