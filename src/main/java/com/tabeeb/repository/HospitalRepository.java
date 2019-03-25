package com.tabeeb.repository;

import com.tabeeb.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    @Query("select h from Hospital h where h.uuid=:uuid")
    Optional<Hospital> findByUuid(@Param("uuid") String uuid);
}
