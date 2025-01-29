package com.example.pharmacy.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class uppat {
    private String name;
    private int age;
    private Date lastVisit;
    private String doctorName;
}
