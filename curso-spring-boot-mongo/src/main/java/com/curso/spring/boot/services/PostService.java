package com.curso.spring.boot.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.boot.domain.Post;
import com.curso.spring.boot.repository.PostRepository;

@Service
public class PostService extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PostRepository repo;
	
	public Optional<Post> findById(String id) {
		Optional<Post> post = repo.findById(id);	
//		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		return post;
	}

	public List<Post> findByTitle(String text) {
		/**
		 * select com query methods
		 */
//		return repo.findByTitleContainingIgnoreCase(text);
		

		/**
		 * select com query fields
		 */
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
