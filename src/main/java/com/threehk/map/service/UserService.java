package com.threehk.map.service;

import com.threehk.map.model.Role;
import com.threehk.map.model.User;
import com.threehk.map.repository.RoleRepository;
import com.threehk.map.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(RoleRepository roleRepository,UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String username, String password, List<Long> roleId) {
        List<Role> roles = roleRepository.findAllById(roleId);
        User user = new User(username,password,roles);
        return userRepository.save(user);
    }

    @Override
    public boolean removeUser(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (EmptyResultDataAccessException e){
            logger.error("Error : ",e);
            return false;
        }
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
