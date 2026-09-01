package com.leandrolima.mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrolima.mongodb.domain.User;
import com.leandrolima.mongodb.repository.UserRepository;
import com.leandrolima.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) { 
	    return repo.findById(id)
	        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	}


