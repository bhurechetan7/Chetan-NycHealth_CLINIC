package com.nyc.health.Clinic.Service;
import com.nyc.health.Clinic.EntityClinic.Clinic;
import com.nyc.health.Clinic.Exception.ClinicNotFoundException;
import com.nyc.health.Clinic.Repository.ClinicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceImpl implements ClinicServices {

    @Autowired
    private ClinicRepo clinicRepo;

    //GET ALL CLINIC DETAILS
    public List<Clinic> getAllClinic() {
        return clinicRepo.findAll();
    }

    //Post the clinic Information
    public Clinic registerNewClinic(Clinic newClinic) {
        return clinicRepo.save(newClinic);
    }

    //New Update Request
     public Clinic updateClinicRecord( Clinic newClinic,Integer id){

                 return clinicRepo.findById(id)
                         .map(clinic -> {
                             clinic.setName(newClinic.getName());
                             clinic.setAddress(newClinic.getAddress());
                             clinic.setEmail(newClinic.getEmail());
                             clinic.setPhoneNumber(newClinic.getPhoneNumber());
                             return clinicRepo.save(clinic);
                         })
                         .orElseGet(()-> clinicRepo.save(newClinic));
     }

    //Delete Clinic information By Id
    public void deleteClinic(Integer id) {
        clinicRepo.deleteById(id);
    }

    //Get Clinic Information By Id
    public Clinic getClinicById(Integer id) {

           return clinicRepo.findById(id)
                   .orElseThrow(()-> new ClinicNotFoundException("No Clinic with id : " +  id  + "is found in Record"));

    }
}