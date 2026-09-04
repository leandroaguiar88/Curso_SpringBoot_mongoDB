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
import com.leandrolima.mongodb.dto.CommentDTO;
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
		
		CommentDTO c1 = new CommentDTO("Sucesso no projeto", sdf.parse("24/08/2026"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Se precisar de ajuda conte comigo", sdf.parse("25/08/2026"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("também adoro esse jogo depois, mande link do git", sdf.parse("26/08/2026"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
	
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
