package com.tabeeb.service;

import com.tabeeb.entity.Hospital;
import com.tabeeb.exception.ApplicationException;
import com.tabeeb.repository.HospitalRepository;
import com.tabeeb.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class HospitalService {
    @Autowired HospitalRepository hospitalRepository;
    public void createHospital(Hospital hospital) {
        hospital.setUuid(UUID.randomUUID().toString());
        hospitalRepository.save(hospital);
    }

    public List<Hospital> fetchAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital fetchHospital(String uuid){
        Optional<Hospital> hospital =  hospitalRepository.findByUuid(uuid);
        if(!hospital.isPresent())
            throw new ApplicationException(Constant.ERROR_HOSPITAL_DOES_NOT_EXIST);

        return hospital.get();
    }

}
