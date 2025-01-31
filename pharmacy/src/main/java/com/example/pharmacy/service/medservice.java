package com.example.pharmacy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;

import com.example.pharmacy.DTO.addmed;
import com.example.pharmacy.DTO.medinfo;
import com.example.pharmacy.DTO.purchase;
import com.example.pharmacy.DTO.status;
import com.example.pharmacy.DTO.stock;
import com.example.pharmacy.entity.distibutor;
import com.example.pharmacy.entity.medicine;
import com.example.pharmacy.entity.patient;
import com.example.pharmacy.entity.suppliers;
import com.example.pharmacy.repository.distrepo;
import com.example.pharmacy.repository.medrep;
import com.example.pharmacy.repository.patientrepo;
import com.example.pharmacy.repository.supprepo;

@Service
public class medservice {
    @Autowired
    private medrep repo;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private patientrepo pepo;
    @Autowired
    private supprepo sepo;
    @Autowired
    private distrepo dist;

    public void addnewmed(addmed temp)
    {
        String tempId=temp.getId();
        Date tempdate=temp.getExpiDate();
        int tempsup=temp.getSupplier_id();
        if(sepo.findBySupplierId(tempsup)==null)
        {
            System.out.println("the supplier doesnt exist");
        }
        else{
        boolean exist=repo.existsByIdAndExpiDateAndSupplierId(tempId,tempdate,tempsup);
        if(exist)
        {
            medicine tempmed=repo.getByIdAndExpiDateAndSupplierId(tempId,tempdate,tempsup);
            try
            {
                tempmed.setQuantity(tempmed.getQuantity()+temp.getQuantity());
                repo.save(tempmed);
                distibutor field=new distibutor();
                field.setSupplierId(temp.getSupplier_id());
                field.setItem(temp.getName());
                field.setQuantity(temp.getQuantity());
                field.setRemaining(temp.getQuantity());
                String supname=sepo.findBySupplierId(tempsup).getName();
                field.setName(supname);
                field.setPurchasedDate(new Date());
                dist.save(field);


            }
            catch(Exception e)
            {
                System.out.println("There was an error while updating the '" + tempmed.getName() + "'");

            }
        }
        else
        {
            medicine newmed=new medicine();
            newmed.setId(temp.getId());
            newmed.setName(temp.getName());
            newmed.setDescription(temp.getDescription());
            newmed.setPrice(temp.getPrice());
            newmed.setQuantity(temp.getQuantity());
            newmed.setSupplierId(temp.getSupplier_id());
            newmed.setExpiDate(temp.getExpiDate());
            repo.save(newmed);
            distibutor field=new distibutor();
            field.setSupplierId(temp.getSupplier_id());
            field.setItem(temp.getName());
            field.setQuantity(temp.getQuantity());
            field.setRemaining(temp.getQuantity());
            String supname=sepo.findBySupplierId(tempsup).getName();
            field.setName(supname);
            field.setPurchasedDate(new Date());
            dist.save(field);
        }
    }

    }
    public List<stock> getAllMedicines()
    {
        GroupOperation groupByMedicineName = Aggregation.group("name")
                .sum("quantity").as("totalQuantity");
        Aggregation aggregation = Aggregation.newAggregation(groupByMedicineName);
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "medicines", Map.class);

        // Access the results
        List<stock> stockList = new ArrayList<>();
        List<Map> resultList = results.getMappedResults();
        for (Map map : resultList) {
            stock tempsto=new stock();
            tempsto.setName((String)(map.get("_id")));
            tempsto.setQuantity((Integer)map.get("totalQuantity"));
            stockList.add(tempsto);
        }
        return stockList;
    }
    public medinfo getinfo(String id)
    {
        medicine tempmed=repo.findById(id);
        medinfo temp=new medinfo();
        temp.setId(id);
        temp.setName(tempmed.getName());
        temp.setDescription(tempmed.getDescription());
        temp.setPrice(tempmed.getPrice());
        temp.setQuantity(tempmed.getQuantity());
        temp.setSupplierId(tempmed.getSupplierId());
        temp.setExpiDate(tempmed.getExpiDate());
        return temp;
    }
    public void deletemed(String id)
    {
        try
        {
            repo.deleteById(id);
        }
        catch(Exception e)
        {
            System.out.println("an error occured while deleting the medicine");
        }
    }
    public int purchaseMedicine(purchase data)
    {
        medicine stock=repo.getByIdAndExpiDate(data.getId(), data.getExpiDate());
        if(data.getQuantity()>stock.getQuantity())
        {
            System.out.println("requested quantity for the medicine isnt available");
            return 0;
        }
        int totalAmount=data.getQuantity()*stock.getPrice();
        stock.setQuantity(stock.getQuantity()-data.getQuantity());
        repo.save(stock);
        distibutor sup=dist.findBySupplierId(data.getSupplierId());
        sup.setRemaining(sup.getRemaining()-data.getQuantity());
        sup.setItemSold(sup.getItemSold()+data.getQuantity());
        dist.save(sup);
        patient pat=pepo.findByName(data.getPatientName());
        if(pat!=null)
        {
            List<String>med=pat.getMedicinePurchased();
            med.add(data.getName());
            pat.setMedicinePurchased(med);
        }
        else
        {
            List<String>med=new ArrayList<>();
            med.add(data.getName());
            patient patt=new patient();
            patt.setName(data.getPatientName());
            patt.setMedicinePurchased(med);
            pepo.save(patt);
        }
        return totalAmount;
    }
    public status getstatus(String id)
    {
        medicine stock=repo.findById(id);
        int quantity=stock.getQuantity();
        status stat=new status();
        if(quantity<50)
        {
            stat.setStatus("low");
        }
        else
        {
            stat.setStatus("high");
        }
        stat.setQuantity(quantity);
        return stat;
    }
}
