package com.leandrolima.mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrolima.mongodb.domain.User;
import com.leandrolima.mongodb.dto.UserDTO;
import com.leandrolima.mongodb.repository.UserRepository;
import com.leandrolima.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
		return repo.insert(obj);

	}
	public User update(User obj) {
	    User newObj = repo.findById(obj.getId())
	        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	    
	    updateData(newObj, obj);
	    return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());

	}

}
