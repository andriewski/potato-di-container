package by.mark.potato.service;

import by.mark.potato.annotation.Potato;
import by.mark.potato.dao.UserDao;
import by.mark.potato.model.User;

@SuppressWarnings("unused")
@Potato(name = "userService")
public class UserService {

    private UserDao userDao;

    // for the test sake
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    // for the test sake
    public UserDao getUserDao() {
        return userDao;
    }
}
