package org.desarrolladorslp.workshops.springboot.service.impl;

import org.desarrolladorslp.workshops.springboot.models.User;
import org.desarrolladorslp.workshops.springboot.repository.UserRepository;
import org.desarrolladorslp.workshops.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(String email, String name) {
        User user = new User();

        user.setEmail(email);
        user.setName(name);

        return userRepository.save(user);
    }

    @Override
    public User findById(Long userID) {
        return userRepository.findById(userID).
                orElseThrow(() -> new EntityNotFoundException("user not found HAHA!!"));
    }

    @Override
    @Transactional
    public void deleteUser(Long userID) {
        User user = findById(userID);
        userRepository.delete(user);
    }
}
