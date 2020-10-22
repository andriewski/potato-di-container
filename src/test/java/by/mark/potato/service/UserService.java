package by.mark.potato.service;

import by.mark.potato.annotation.Potato;
import by.mark.potato.dao.UserDao;
import by.mark.potato.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Potato(name = "userService")
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class UserService {

    @Getter
    private UserDao userDao;

    public User createUser(User user) {
        return userDao.createUser(user);
    }
}
