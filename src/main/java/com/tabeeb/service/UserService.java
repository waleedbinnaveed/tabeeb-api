package com.tabeeb.service;

import com.tabeeb.entity.User;
import com.tabeeb.exception.ApplicationException;
import com.tabeeb.repository.UserRepository;
import com.tabeeb.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(String username, String name,String gender,Integer age, String password, String mobileNo, String userType) {


        if(findByUserName(username).isPresent())
            throw new ApplicationException(Constant.ERROR_USER_WITH_SAME_USERNAME_ALREADY_EXISTS);

        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setMobileNo(mobileNo);
        user.setUserType(userType);
        user.setUuid(UUID.randomUUID().toString());


        userRepository.save(user);
        return user;
    }


    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User fetchUser(String uuid) {
        Optional<User> user = findByUuid(uuid);
        if(!user.isPresent())
            throw new ApplicationException(Constant.ERROR_USER_DOES_NOT_EXIST);

        return user.get();

    }

    private Optional<User> findByUuid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    private Optional<User> findByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
