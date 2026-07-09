package org.example.assessment.model.validator;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class GeneralValidator {

    public static void checkEmail(String emailAddress){
        String pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(!Pattern.matches(pattern, emailAddress)){
            throw new IllegalArgumentException(String.format("Invalid email regex validation. Regex:[%s], Value: [%s]", pattern, emailAddress));
        }
    }
    public static void checkIsbnNumber(String isbnNumber){
        String pattern = "\\d{3}-\\d{1}-\\d{4}-\\d{4}-\\d{1}";
        if(!Pattern.matches(pattern, isbnNumber)){
            throw new IllegalArgumentException(String.format("Invalid ISBN Number regex validation. Regex:[%s], Value: [%s]", pattern, isbnNumber));
        }
    }
}
