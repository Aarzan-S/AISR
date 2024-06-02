package com.aisr.initial.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * THis class has methods that validates email and password fields
 */
public class Validator {
    private static final String EMAIL_VALIDATION_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    private static final String PHONE_NO_VALIDATION_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    /**
     * Checks whether provided email is valid or not
     * @param email
     * @return true is emila is valid else false
     */
    public static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks whether provided phoneNumber is valid or not
     * @param phoneNumber
     * @return true is phone number is valid else false
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern phoneNoPattern = Pattern.compile(PHONE_NO_VALIDATION_REGEX);
        Matcher matcher = phoneNoPattern.matcher(phoneNumber);
        return matcher.matches();
    }
}