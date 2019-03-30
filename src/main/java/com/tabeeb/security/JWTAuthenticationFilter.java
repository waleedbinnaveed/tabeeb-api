package com.tabeeb.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tabeeb.dto.AuthDTO;
import com.tabeeb.entity.User;
import com.tabeeb.exception.ApplicationException;
import com.tabeeb.util.Constant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        User userCred = null;
        try {
            userCred = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            throw new ApplicationException(Constant.ERROR_OCCURED);
        }


        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCred.getUsername(),
                        userCred.getPassword(),
                        new ArrayList<>() //authorities
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Constant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, Constant.SECRET)
                .compact();
        response.addHeader(Constant.HEADER_STRING,Constant.TOKEN_PREFIX + token);

        AuthDTO authDTO = new AuthDTO();
        authDTO.setToken(token);
        authDTO.setUsername(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername());

        String authDTOString = new Gson().toJson(authDTO);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(authDTOString);
        out.flush();



    }
}



//    String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
//
//    Map<String,Object> map = new HashMap<>();
//        map.put("profile","DOCTOR");
//                map.put("username",username);
//
//                String token = Jwts.builder()
//                .setClaims(map)
//                .setExpiration(new Date(System.currentTimeMillis() + Constant.EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS256, Constant.SECRET)
//                .compact();
//                response.addHeader(Constant.HEADER_STRING,Constant.TOKEN_PREFIX + token);
//                response.getWriter().write(token);