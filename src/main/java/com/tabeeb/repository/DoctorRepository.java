package com.tabeeb.repository;

import com.tabeeb.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Query("select d from Doctor d where d.user.uuid=:uuid")
    Optional<Doctor> findByUuid(@Param("uuid") String uuid);

    @Query("select d from Doctor d where d.hospital.uuid=:hospitalUuid")
    List<Doctor> findByHospitalUuid(@Param("hospitalUuid") String hospitalUuid);

    @Query("select d from Doctor d where d.user.username=:username")
    Optional<Doctor> findByUsername(@Param("username") String username);
}
