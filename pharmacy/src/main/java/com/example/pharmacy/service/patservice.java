package com.example.pharmacy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.DTO.uppat;
import com.example.pharmacy.entity.patient;
import com.example.pharmacy.repository.patientrepo;

@Service
public class patservice {
    @Autowired
    private patientrepo repo;
    public void createpatient(patient data)
    {
        patient temp=repo.findByName(data.getName());
        if(temp!=null)
        {
            System.out.println("patient already exists");
        }
        else
        {
            patient newpat=new patient();
            newpat.setName(data.getName());
            newpat.setAge(data.getAge());
            newpat.setLastVisit(data.getLastVisit());
            newpat.setGender(data.getGender());
            repo.save(newpat);
        }
    }
    public List<patient> getallpatientdata()
    {
        return repo.findAll();
    }
    public patient getpatientdetails(String name)
    {
        patient temp=repo.findByName(name);
        if(temp!=null)
        {
            return temp;
        }
        else
        {
            return null;
        }
    }
    public void updatepatientdetails(uppat data)
    {
        patient temp=repo.findByName(data.getName());
        if(temp!=null)
        {
            temp.setAge(data.getAge());
            temp.setDoctorName(data.getDoctorName());
            temp.setLastVisit(data.getLastVisit());
            repo.save(temp);
        }
        else
        {
            System.out.println("patient doesn't exists");
        }
    }
    public void deletepatient(String name)
    {
        patient temp=repo.findByName(name);
        if(temp!=null)
        {
            repo.deleteByName(name);
        }
        else
        {
            System.out.println("patient doesn't exists");
        }
    }
}
