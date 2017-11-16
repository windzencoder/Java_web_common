package com.wz.demo.service;

import com.wz.demo.model.User;

public interface UserService{

    User findByUsername(String username);

}
