package com.juanOrtizJaimes.springBootAplicacion.service;

import com.juanOrtizJaimes.springBootAplicacion.entity.User;

public interface UserService {
	
	public Iterable<User> getAllUsers();

	public User createUser(User user) throws Exception;
	
	

}
