package dao;

import src.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static Map<String, User> ADMIN_USERS = new HashMap<>();
    public static Map<String, User> OPERATOR_USERS = new HashMap<>();
    public static Map<String, User> USERS = new HashMap<>();

    static{
        User admin = new User("admin","123");
        ADMIN_USERS.put(admin.getUsername(),admin);
        User user = new User("user","123");
        USERS.put(user.getUsername(),user);
        User operator = new User("operator","123");
        OPERATOR_USERS.put(operator.getUsername(),operator);

    }
}
