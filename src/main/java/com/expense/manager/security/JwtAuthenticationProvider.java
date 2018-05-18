package com.expense.manager.security;

import com.expense.manager.bo.User;
import com.expense.manager.exception.IncorrectJwtTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();
        logger.debug("auth token is {}", token);
        User user = JwtTokenHandler.getInstance().parse(token);
        logger.debug("user  is {}", user);
        if(user == null) throw new IncorrectJwtTokenException("Incorrect JWT token");
        return user;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
