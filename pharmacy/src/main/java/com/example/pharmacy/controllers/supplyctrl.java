package com.example.pharmacy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.entity.suppliers;
import com.example.pharmacy.service.suppservice;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins="*")
public class supplyctrl {
    @Autowired
    private suppservice service;
    @PostMapping("/create")
    public ResponseEntity<String> createsupplier(@RequestBody suppliers data) {
        String result = service.createsupplier(data);

        if ("new supplier created".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Supplier created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create supplier");
        }
    }
    @PostMapping("/delete/{name}")
    public ResponseEntity<String> deletesupplier(@PathVariable String name)
    {
        String result = service.deletesupplier(name);
        if ("supplier deleted sucessfully".equals(result)) {
            return ResponseEntity.status(HttpStatus.OK).body("Supplier created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete supplier");
        }
    }

}
