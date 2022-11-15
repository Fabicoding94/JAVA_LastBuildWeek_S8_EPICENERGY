package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.User;
import com.example.lastbuildweek.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	public void save( User u) {
		String psw = u.getPassword();
		u.setPassword(encoder.encode(psw));
		userRepository.save(u);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getById(Long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if ( user.isEmpty() )
			throw new Exception("User not available");
		return user.get();
	}

	public void delete(Long id) throws Exception {
		Optional<User> u = userRepository.findById(id);
		if (u.isPresent()) {
			userRepository.delete(u.get());
		} else {
			throw new Exception("Utente non trovato");
		}
	}

	public void update(User u) {
		userRepository.save(u);
	}

	public User findByUsername(String username) throws Exception {
		Optional<User> user = userRepository.findByUsername(username);
		if ( user.isEmpty() )
			throw new Exception("No user with that username found");
		return user.get();
	}

	public Page<User> getAllPaginate(Pageable p) {
		return userRepository.findAll(p);
	}
}