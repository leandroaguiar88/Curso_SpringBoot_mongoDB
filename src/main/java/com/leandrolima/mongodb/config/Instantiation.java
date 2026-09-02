package com.leandrolima.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.leandrolima.mongodb.domain.Post;
import com.leandrolima.mongodb.domain.User;
import com.leandrolima.mongodb.dto.AuthorDTO;
import com.leandrolima.mongodb.repository.PostRepository;
import com.leandrolima.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
			
		 
		Post post1 = new Post(null, sdf.parse("21/08/2026"),"Novo projeto java", "Estou muito animado!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/08/2026"),"Projeto de Xadrez", "Adoro esse jogo!", new AuthorDTO(maria));
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
	
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
