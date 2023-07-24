package dev.c3rd.supercart.service;

import dev.c3rd.supercart.model.User;

import java.util.List;

public interface IUserService {

    List<User> all();
    User store(User user);
    User one(Long id);
    User update(User user, Long id);
    void delete(Long id);

}
