package com.leandrolima.mongodb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrolima.mongodb.domain.Post;
import com.leandrolima.mongodb.repository.PostRepository;
import com.leandrolima.mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	
	public Post findById(String id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}



	

	

}
