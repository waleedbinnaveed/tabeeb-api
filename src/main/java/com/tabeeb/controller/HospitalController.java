package com.tabeeb.controller;

import com.tabeeb.entity.Hospital;
import com.tabeeb.service.HospitalService;
import com.tabeeb.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class HospitalController {

    @Autowired private HospitalService hospitalService;

    @PostMapping("/hospital")
    public ResponseEntity<String> createHospital(@RequestBody @Valid Hospital hospital)
    {
        hospitalService.createHospital(hospital);
        return ResponseEntity.ok(Constant.SUCCESS_HOSPITAL_CREATED);
    }

    @GetMapping("/hospital")
    public ResponseEntity<List<Hospital>> getAllHospitals()
    {
        return ResponseEntity.ok(hospitalService.fetchAllHospitals());
    }

    @GetMapping("/hospital/{uuid}")
    public ResponseEntity<Hospital> getHospital(@PathVariable("uuid") String uuid)
    {
        return ResponseEntity.ok(hospitalService.fetchHospital(uuid));
    }
}
