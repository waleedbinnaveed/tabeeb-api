package com.tabeeb.repository;

import com.tabeeb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    @Query("select u from User u where u.username=:username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u where u.uuid=:uuid")
    Optional<User> findByUuid(@Param("uuid") String uuid);
}