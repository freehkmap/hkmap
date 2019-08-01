package com.threehk.map.config.security;

import com.threehk.map.service.login.CustomUserDetails;
import com.threehk.map.service.login.CustomUserDetailsService;
import com.threehk.map.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Value("${passwordSalt}")
    private String passwordSalt;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            String password = (String) authentication.getCredentials();
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
            if (!checkPassword(userDetails.getPassword(), password)) {
                throw new BadCredentialsException("password incorrect");
            }
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }catch (NullPointerException e){
            logger.error("Error : ",e);
        }catch (BadCredentialsException e){
            logger.error("Error : ",e);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean checkPassword(String userPassword,String password){
        String hashedPassword = MD5.getMD5(password + passwordSalt);
        return userPassword.equals(hashedPassword);
    }
}
