package com.example.pharmacy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.DTO.uppat;
import com.example.pharmacy.entity.patient;
import com.example.pharmacy.service.patservice;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class patctrl {
    @Autowired
    private patservice service;
    @PostMapping("/create")
    public String createpatient(@RequestBody patient data)
    {
        service.createpatient(data);
        return "patient created sucessfully";
    }
    @GetMapping("/getAll")
    public List<patient> getAllpatient()
    {
        return service.getallpatientdata();
    }
    @PostMapping("/update")
    public String updatedata(@RequestBody uppat data)
    {
        service.updatepatientdetails(data);
        return "patient data updated sucessfully";
    }
    @PostMapping("/delete/{name}")
    public String deletedata(@PathVariable String name)
    {
        service.deletepatient(name);
        return "patient data deleted sucessfully";
    }
}
