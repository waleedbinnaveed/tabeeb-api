package com.tabeeb.service;

import com.tabeeb.entity.User;
import com.tabeeb.exception.ApplicationException;
import com.tabeeb.repository.UserRepository;
import com.tabeeb.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent())
            throw new ApplicationException(Constant.ERROR_USER_DOES_NOT_EXIST);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.get().getUserType()));
        
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getPassword()
        , grantedAuthorities);

    }
}
