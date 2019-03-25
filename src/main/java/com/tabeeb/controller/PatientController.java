package com.tabeeb.controller;

import com.tabeeb.entity.Patient;
import com.tabeeb.service.PatientService;
import com.tabeeb.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/patient/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid Patient patientUser) {
        patientService.createPatientUser(patientUser);
        return ResponseEntity.ok(Constant.SUCCESS_USER_CREATED);
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.fetchAllPatients());
    }


    @GetMapping("/patient/{uuid}")
    public ResponseEntity<Patient> getPatient(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(patientService.fetchPatient(uuid));
    }


    @PutMapping("/patient/{uuid}/diagnose")
    public ResponseEntity<String> addDiagnose(@PathVariable("uuid") String uuid,
                                              @RequestParam("diagnose") String diagnose,
                                              @RequestParam("doctor-uuid") String doctorUuid) {
        patientService.addDiagnose(uuid, doctorUuid, diagnose);

        return ResponseEntity.ok(Constant.SUCCESS_DIAGNOSE_ADDED);
    }

    @GetMapping("/patient/doctor/{doctor-uuid}")
    public ResponseEntity<List<Patient>> getDoctorPatients(@PathVariable("doctor-uuid") String doctorUuid) {
        return ResponseEntity.ok(patientService.fetchPatientsByDoctor(doctorUuid));
    }

}
