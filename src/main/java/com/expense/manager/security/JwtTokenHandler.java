package com.expense.manager.security;

import com.expense.manager.bo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class JwtTokenHandler {
    private static final Logger logger  = LoggerFactory.getLogger(JwtTokenHandler.class);

    public static final String SECRET   = "EM-12u98RT3@87DC3";
    public static final String USER_ID  = "userId";
    public static final String ROLES    = "roles";
    public static final String GEN_TIME = "time";

    public static final int THRESHOLD   = 30 * 60 * 1000;

    private JwtTokenHandler(){
    }

    public static JwtTokenHandler tokenParser = new JwtTokenHandler();

    public static JwtTokenHandler getInstance(){
        return tokenParser;
    }

    public User parse(String token){
        try{
            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            User user = new User();
            user.setUserName(body.getSubject());
            long genTime = body.get(GEN_TIME, Long.class);
            long curTime = new Date().getTime();
            if(curTime - genTime > THRESHOLD){
                throw new RuntimeException("token expired");
            }
            user.setUserId(Long.parseLong(String.valueOf(body.get(USER_ID))));
            Collection<GrantedAuthority> roles = new ArrayList<>();
            for(String role : (ArrayList<String>)body.get(ROLES, ArrayList.class)){
                roles.add(new SimpleGrantedAuthority(role));
            }
            user.setRoles(roles);
            return user;
        }catch (Exception e) {
            logger.error("Unable to parse token", e);
            return null;
        }
    }

    public String generate(User user){
        try{
            Claims claims = Jwts.claims().setSubject(user.getUsername());
            claims.put(USER_ID, user.getUserId());

            Iterator itr = user.getAuthorities().iterator();
            List<String> roles = new ArrayList<String>();
            while(itr.hasNext()){
                roles.add(((GrantedAuthority)itr.next()).getAuthority());
            }
            claims.put(ROLES, roles);
            claims.put(GEN_TIME, new Date().getTime());

            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();

        }catch (Exception e){
            logger.error("error in generating token ", e);
        }
        return null;
    }

}
