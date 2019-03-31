package com.tabeeb.service;

import com.tabeeb.entity.Doctor;
import com.tabeeb.entity.Hospital;
import com.tabeeb.entity.User;
import com.tabeeb.exception.ApplicationException;
import com.tabeeb.repository.DoctorRepository;
import com.tabeeb.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class DoctorService {
    @Autowired private UserService userService;
    @Autowired private HospitalService hospitalService;
    @Autowired private DoctorRepository doctorRepository;

    public void createDoctorUser(Doctor doctorUser) {
        Doctor doctor = new Doctor();

        User user = userService.createUser(
                doctorUser.getUser().getUsername(),
                doctorUser.getUser().getName(),
                doctorUser.getUser().getGender(),
                doctorUser.getUser().getAge(),
                doctorUser.getUser().getPassword(),
                doctorUser.getUser().getMobileNo(),
                Constant.USER_TYPE_DOCTOR
        );

        Hospital hospital = hospitalService.fetchHospital(doctorUser.getHospital().getUuid());
        doctor.setKind(doctorUser.getKind());
        doctor.setUser(user);
        doctor.setHospital(hospital);

        doctorRepository.save(doctor);
    }

    public Doctor fetchDoctor(String uuid){
        Optional<Doctor> doctor =  doctorRepository.findByUuid(uuid);
        if(!doctor.isPresent())
            throw new ApplicationException(Constant.ERROR_DOCTOR_DOES_NOT_EXIST);

        return doctor.get();
    }

    public List<Doctor> fetchAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> fetchDoctorsByHospital(String hospitalUuid) {
        hospitalService.fetchHospital(hospitalUuid); //validate hospital uuid
        return doctorRepository.findByHospitalUuid(hospitalUuid);
    }

    public Doctor fetchDoctorByUserName(String username) {
        Optional<Doctor> doctor =  doctorRepository.findByUsername(username);
        if(!doctor.isPresent())
            throw new ApplicationException(Constant.ERROR_DOCTOR_DOES_NOT_EXIST);

        return doctor.get();
    }
}
