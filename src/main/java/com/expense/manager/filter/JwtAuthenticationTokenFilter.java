package com.expense.manager.filter;

import com.expense.manager.exception.JwtTokenMissingException;
import com.expense.manager.security.JwtAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        String header = req.getHeader(AUTHORIZATION_HEADER);
        if(header == null || !header.startsWith("Bearer ")){
            throw new JwtTokenMissingException("JWT token is not present into header");
        }
        String token = header.substring(7);
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
