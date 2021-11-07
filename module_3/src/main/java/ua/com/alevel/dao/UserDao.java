package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByPhoneNumberBothEmailBothPassword(String phoneNumber, String email, String password);

    void addNewUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    void updateUser(User user) throws SQLException;
}