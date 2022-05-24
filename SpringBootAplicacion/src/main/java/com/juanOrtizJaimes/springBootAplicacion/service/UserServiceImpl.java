package com.juanOrtizJaimes.springBootAplicacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanOrtizJaimes.springBootAplicacion.entity.User;
import com.juanOrtizJaimes.springBootAplicacion.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

	
}
