package com.wz.shiroweb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wz.shiroweb.bean.User;
import com.wz.shiroweb.dao.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserTest {
	
	@Autowired
	private UserMapper userMapper;
	
    @Test
    public void testCRUD() {
    		
    		userMapper.insert(new User("test", "123", "test", 1));
    	
    }

}
