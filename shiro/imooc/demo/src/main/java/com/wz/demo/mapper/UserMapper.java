package com.wz.demo.mapper;

import com.wz.demo.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByUsername(@Param("username") String username);

}
