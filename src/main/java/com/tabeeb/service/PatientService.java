package com.tabeeb.service;

import com.tabeeb.entity.Doctor;
import com.tabeeb.entity.Patient;
import com.tabeeb.entity.User;
import com.tabeeb.exception.ApplicationException;
import com.tabeeb.repository.PatientRepository;
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
public class PatientService {

    @Autowired private UserService userService;
    @Autowired private DoctorService doctorService;
    @Autowired private PatientRepository patientRepository;

    public void createPatientUser(Patient patientUser) {
        Patient patient = new Patient();

        User user = userService.createUser(
                patientUser.getUser().getUsername(),
                patientUser.getUser().getName(),
                patientUser.getUser().getGender(),
                patientUser.getUser().getAge(),
                patientUser.getUser().getPassword(),
                patientUser.getUser().getMobileNo(),
                Constant.USER_TYPE_PATIENT
        );

        Doctor doctor = doctorService.fetchDoctor(patientUser.getDoctor().getUser().getUuid());

        patient.setUser(user);
        patient.setDoctor(doctor);

        patientRepository.save(patient);


    }

    public List<Patient> fetchAllPatients() {
        return patientRepository.findAll();
    }

    public Patient fetchPatient(String uuid){
        Optional<Patient> patient =  patientRepository.findByUuid(uuid);

        if(!patient.isPresent())
            throw new ApplicationException(Constant.ERROR_PATIENT_DOES_NOT_EXIST);

        return patient.get();
    }

    public void addDiagnose(String uuid, String doctorUuid,String diagnose) {
        Patient patient = fetchPatient(uuid);
        Doctor doctor = doctorService.fetchDoctor(doctorUuid); //called for validation if doctor exists

        if(patient.getDoctor()!=doctor)
            throw new ApplicationException(Constant.ERROR_INVALID_USER);
        else
        {
            patient.setDiagnose(diagnose);
            patientRepository.save(patient);
        }

    }

    public List<Patient> fetchPatientsByDoctor(String doctorUuid) {
        doctorService.fetchDoctor(doctorUuid); //validation doctor uuid
      return patientRepository.findByDoctorUuid(doctorUuid);
    }

    public Patient fetchPatientByUsername(String username) {
        Optional<Patient> patient = patientRepository.fetchByUsername(username);
        if(!patient.isPresent())
            throw new ApplicationException(Constant.ERROR_PATIENT_DOES_NOT_EXIST);

        return patient.get();
    }
}
