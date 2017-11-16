package com.wz.demo.service.impl;

import com.wz.demo.mapper.UserMapper;
import com.wz.demo.model.User;
import com.wz.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
