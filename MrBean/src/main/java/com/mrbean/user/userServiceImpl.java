package com.mrbean.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userDAO userDAO;

    @Override
    public void createUser(userVO user) {
        userDAO.createUser(user); // DAO 호출
    }

    @Override
    public userVO getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public List<userVO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(userVO user) {
        userDAO.updateUser(user);
    }
}
