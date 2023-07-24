package dev.c3rd.supercart.service.impl;

import dev.c3rd.supercart.exception.UserNotFoundException;
import dev.c3rd.supercart.model.User;
import dev.c3rd.supercart.repository.UserRepository;
import dev.c3rd.supercart.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Override
    public User store(User user) {
        return userRepository.save(user);
    }

    @Override
    public User one(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User update(User user, Long id) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        userRepository.save(existingUser);

        return existingUser;

    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
    }
}
