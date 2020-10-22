package by.mark.potato.service;

import by.mark.potato.dao.UserDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class ContractService {

    @Getter
    private UserDao userDao;
}
