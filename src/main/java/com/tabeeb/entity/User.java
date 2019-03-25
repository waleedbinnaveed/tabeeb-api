package com.tabeeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tabeeb.util.Constant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false,updatable = false,length = 36,unique = true)
    private String uuid;

    @NotBlank @Column(unique = true)
    private String username;

    private String name ;

    @NotBlank
    private String gender;

    private Integer age;

    private String password;

    @Pattern(regexp = Constant.MOBILE_NO_REGEX)
    private String mobileNo;

    @Column(nullable = false, length = 7)
    private String userType;

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

}
