package com.example.pharmacy.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class purchase {
    private String patientName;
    private String id;
    private String name;
    private String description;
    private int quantity;
    private Date expiDate;
    private int supplierId;
}
