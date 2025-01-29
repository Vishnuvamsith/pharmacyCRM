package com.example.pharmacy.entity;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class patient {
    @Id
    private ObjectId _id;
    private String name;
    private int age;
    private String gender;
    private Date lastVisit;
    private List<String> medicinePurchased;
    private String doctorName;
}
