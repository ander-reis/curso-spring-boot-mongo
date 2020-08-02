package com.curso.spring.boot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.spring.boot.domain.User;
import com.curso.spring.boot.dto.UserDTO;
import com.curso.spring.boot.repository.UserRepository;
import com.curso.spring.boot.services.exception.ObjectNotFoundException;

@Service
public class UserService extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserRepository repo;
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);	
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
//		return null;
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	
}
