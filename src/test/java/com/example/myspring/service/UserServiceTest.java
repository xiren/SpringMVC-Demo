package com.example.myspring.service;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myspring.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserServiceTest extends TestCase {
	@Autowired
	private UserService userService;
	
	@Test
	public void hasMatchUserTest(){
		boolean user1 = userService.hasMatchUser("admin", "123456");
		boolean user2 = userService.hasMatchUser("admin", "111111");
		assertTrue(user1);
		assertFalse(user2);
	}
	@Test
	public void findUserbyNameTest(){
		User user = userService.findUserbyName("admin");
		assertNotNull(user);
	}
}
