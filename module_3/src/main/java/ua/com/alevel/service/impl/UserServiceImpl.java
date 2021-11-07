package ua.com.alevel.service.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dao.impl.UserDaoImpl;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.SecurityUtil;

import java.util.Optional;

import static ua.com.alevel.util.SessionsUtil.saveInputInformationSession;

@Log4j2
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    private static User currentLoginCliUser;

    @Override
    public void register(String firstName, String lastName, String email, String phoneNumber, String password) {
        User user = User.builder()
                .userFirstName(firstName)
                .userLastName(lastName)
                .userEmail(email)
                .userTelephoneNumber(phoneNumber)
                .userPassword(SecurityUtil.hashPassword(password))
                .build();
        saveInputInformationSession(user);
    }

    @Override
    public User getCurrentUser() {
        return currentLoginCliUser;
    }

    @Override
    public Optional<User> login(String userEmail, String userPhoneNumber, String password) {
        Optional<User> user = userDao.findUserByPhoneNumberBothEmailBothPassword(userPhoneNumber, userEmail, SecurityUtil.hashPassword(password));
        user.ifPresent(u -> UserServiceImpl.currentLoginCliUser = u);
        return user;
    }
}
