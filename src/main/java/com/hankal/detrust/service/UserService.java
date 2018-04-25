package com.hankal.detrust.service;

import com.hankal.detrust.dao.UserDao;
import com.hankal.detrust.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<UserInfo> getUsers() {
        return userDao.getAll();
    }

    public UserInfo findByUserName(String mobilePhone) {
        return userDao.findByUserName(mobilePhone);
    }
}
