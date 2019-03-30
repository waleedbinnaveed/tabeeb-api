package com.tabeeb.repository;

import com.tabeeb.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select p from Patient p where p.user.uuid=:uuid")
    Optional<Patient> findByUuid(@Param("uuid") String uuid);

    @Query("select p from Patient p where p.doctor.user.uuid=:doctorUuid")
    List<Patient> findByDoctorUuid(@Param("doctorUuid") String doctorUuid);

    @Query("select p from Patient p where p.user.username=:username")
    Optional<Patient> fetchByUsername(@Param("username") String username);
}
