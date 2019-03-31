package com.tabeeb.controller;

import com.tabeeb.entity.Doctor;
import com.tabeeb.service.DoctorService;
import com.tabeeb.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired private DoctorService doctorService;

    @PostMapping("/doctor/register")
    public ResponseEntity<Void> registerUser(@RequestBody @Valid Doctor doctorUser){
        doctorService.createDoctorUser(doctorUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.fetchAllDoctors());
    }


    @GetMapping("/doctor/{uuid}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(doctorService.fetchDoctor(uuid));
    }

    @GetMapping("/doctor/{username}/username")
    public ResponseEntity<Doctor> getDoctorByUserName(@PathVariable("username") String username){
        return ResponseEntity.ok(doctorService.fetchDoctorByUserName(username));
    }

    @GetMapping("/doctor/hospital/{hospital-uuid}")
    public ResponseEntity<List<Doctor>> getHospitalDoctors(@PathVariable("hospital-uuid") String hospitalUuid) {
        return ResponseEntity.ok(doctorService.fetchDoctorsByHospital(hospitalUuid));
    }

}
