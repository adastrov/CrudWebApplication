package com.palagen.secure.users.dao;

import com.palagen.secure.users.model.User;

public interface UserDao {

	User findByUserName(String username);

}