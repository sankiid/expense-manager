package com.expense.manager.validation;

import com.expense.manager.bo.User;
import com.expense.manager.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern;
    private Matcher matcher;

    public UserValidation(){
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    public boolean isValid(User user) {
        if(user == null){
            return false;
        }else if(StringUtils.isBlank(user.getName())){
            return false;
        }else if(StringUtils.isBlank(user.getUsername())){
            return false;
        }else if(StringUtils.isBlank(user.getPassword())){
            return false;
        }else if(user.getPassword().length() > 20){
            return false;
        }else if(user.getName().length() > 50){
            return false;
        }else if(user.getUsername().length() > 50){
            return false;
        }else if(!pattern.matcher(user.getUsername()).matches()){
            return false;
        }else{
            return true;
        }
    }
}
