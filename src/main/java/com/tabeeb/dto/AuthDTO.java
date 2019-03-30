package com.tabeeb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDTO implements Serializable {
    private String token;
    private String username;
    private Integer age;
    private String gender;
    private String mobileNo;
    private String userType;
    private String uuid;
    private Collection<GrantedAuthority> authorities;
}
