package by.mark.potato.dao;

import by.mark.potato.annotation.Potato;
import by.mark.potato.model.User;

@Potato(name = "userDao")
public class UserDao {

    public User createUser(User user) {
        return user;
    }
}
