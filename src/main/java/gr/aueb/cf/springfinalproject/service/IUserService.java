package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.model.User;
import gr.aueb.cf.springfinalproject.service.exceptions.UserAlreadyExistsException;
import gr.aueb.cf.springfinalproject.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {
    User getUserByUsername(String username) throws UserNotFoundException;
    User getUserById(Long id) throws UserNotFoundException;
    User saveUser(User user) throws UserAlreadyExistsException;
    void deleteUser(User user) throws UserNotFoundException;
    User updateUser(User user) throws UserNotFoundException;
    List<User> findAllUsers() throws Exception;
}
