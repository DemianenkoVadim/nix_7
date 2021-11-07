package ua.com.alevel.service;

import ua.com.alevel.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {

    void register(String firstName, String lastName, String email, String phoneNumber, String password) throws SQLException;

    User getCurrentUser();

    Optional<User> login(String userEmail, String userPhoneNumber, String password);
}
