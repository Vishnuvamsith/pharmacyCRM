package com.example.pharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.entity.distibutor;
import com.example.pharmacy.entity.suppliers;
import com.example.pharmacy.repository.supprepo;

@Service
public class suppservice {
    @Autowired
    private supprepo repo;
    public String createsupplier(suppliers data)
    {
        suppliers temp=repo.findByName(data.getName());
        if(temp!=null)
        {
            return "supplier already exist";
        }
        suppliers newsuppy=new suppliers();
        newsuppy.setName(data.getName());
        newsuppy.setSupplierId(data.getSupplierId());
        newsuppy.setEmail(data.getEmail());
        newsuppy.setPhoneNumber(data.getPhoneNumber());
        newsuppy.setAddress(data.getAddress());
        repo.save(newsuppy);
        return "new supplier created";

    }
    public String deletesupplier(String name)
    {
        suppliers temp=repo.findByName(name);
        if(temp!=null)
        {
            repo.deleteByName(name);
            return "supplier deleted sucessfully";
        }
        return "supplier not found";
    }
    public void showdata()
    {

    }
}
