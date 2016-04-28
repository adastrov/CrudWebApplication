package com.palagen.organizer.users.dao;

import com.palagen.organizer.users.model.User;

public interface UserDao {

	User findByUserName(String username);
	void create(User user) throws Exception;
	User get(int id);
}