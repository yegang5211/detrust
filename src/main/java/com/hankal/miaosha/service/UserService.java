package com.hankal.miaosha.service;

import com.hankal.miaosha.dao.UserDao;
import com.hankal.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yegang5211 on 2018/2/9.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean insertUser(){
        User u1 = new User();
        u1.setId(2);
        u1.setName("2222");
        userDao.insertUser(u1);

        User u2 = new User();
        u2.setId(1);
        u2.setName("1111111");
        userDao.insertUser(u2);

        return  true;
    }
}
