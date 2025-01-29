package com.example.pharmacy.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.DTO.addmed;
import com.example.pharmacy.DTO.medinfo;
import com.example.pharmacy.DTO.purchase;
import com.example.pharmacy.DTO.status;
import com.example.pharmacy.DTO.stock;
import com.example.pharmacy.entity.medicine;
import com.example.pharmacy.service.medservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/medicines")
@CrossOrigin(origins = "*")
public class medctrl {
    @Autowired
    private medservice service;
    @PostMapping("/addMedicine")
    public String addMedicine(@RequestBody addmed med)
    {
        service.addnewmed(med);
        return "medicine added sucessfully";
    }
    @GetMapping("/getMedicines")
    public List<stock> getAllMedicines()
    {
        return service.getAllMedicines();
    }
    @GetMapping("/medicine/{id}")
    public medinfo getMedicineDetails(@PathVariable String id)
    {
        return service.getinfo(id);
    }
    @PostMapping("/updateMedicine/{id}")
    public String updateMedicine(@PathVariable String id,@RequestBody medicine updatMedicine)
    {
        return "medicine updated sucessfully";
    }
    @PostMapping("/deleteMedicine/{id}")
    public String deleteMedicine(@PathVariable String id)
    {
        service.deletemed(id);
        return "medicine deleted sucessfully";
    }
    @PostMapping("/purchaseMedicine")
    public int purchasemed(@RequestBody purchase data)
    {
        int billAmount=service.purchaseMedicine(data);
        return billAmount;
    }
    @PostMapping("/getMedicineStatus/{id}")
    public status getstatus(@PathVariable String id)
    {
        return service.getstatus(id);
    }
   
    
}
