package ua.com.alevel.user;

import org.apache.commons.lang3.*;

public class UserName {

    public void showUserName() {
        System.out.println("Hello " + SystemUtils.getUserName());
    }
}
