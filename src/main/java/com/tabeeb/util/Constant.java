package com.tabeeb.util;

public final class Constant {
    public static final String MOBILE_NO_REGEX = "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$";
    public static final String ERROR_USER_WITH_SAME_USERNAME_ALREADY_EXISTS = "User with same username already exists";
    public static final String ERROR_USER_DOES_NOT_EXIST = "User does not exist";
    public static final String SUCCESS_USER_CREATED = "User created successfully";
    public static final String SUCCESS_HOSPITAL_CREATED = "Hospital created successfully";
    public static final String ERROR_HOSPITAL_DOES_NOT_EXIST = "Hospital does not exist";
    public static final String USER_TYPE_DOCTOR = "DOCTOR";
    public static final String USER_TYPE_PATIENT = "PATIENT";
    public static final String ERROR_DOCTOR_DOES_NOT_EXIST = "Doctor user does not exist";
    public static final String ERROR_PATIENT_DOES_NOT_EXIST = "Patient user does not exist";
    public static final String ERROR_INVALID_USER = "User is invalid";
    public static final String SUCCESS_DIAGNOSE_ADDED = "Diagnose added successfully";
    public static final String ERROR_OCCURED = "Error occured";


    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final Long EXPIRATION_TIME = 864_000_000l; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user/register";
    public static final String SIGN_UP_URL_DOCTOR = "/api/doctor/register";
    public static final String SIGN_UP_URL_PATIENT = "/api/patient/register";
    public static final String HOSPITAL_URL = "/api/hospital";


}
