package com.threehk.map.service.login;

import com.threehk.map.model.User;
import com.threehk.map.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepository.getUserByUsername(username);
        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("no such user"));

        return optionalUsers.map((User t) -> new CustomUserDetails(t)).get();
    }
}
