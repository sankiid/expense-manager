package com.expense.manager.utils;

import com.expense.manager.bo.User;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    public static User getUser(HttpServletRequest request) {
        return (User)request.getAttribute(StringUtils.USER_ID);
    }
}
