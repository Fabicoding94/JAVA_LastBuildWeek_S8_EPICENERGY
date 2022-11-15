package com.example.java_venerdi_s7.services;

import com.example.java_venerdi_s7.entities.Misurazione;
import com.example.java_venerdi_s7.entities.Sonda;
import com.example.java_venerdi_s7.repository.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SondaService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private SondaRepository repository;

	public void save( Sonda u) {
		String psw = u.getPassword();
		u.setPassword(encoder.encode(psw));
		repository.save(u);
	}

	public List<Sonda> getAll() {
		return repository.findAll();
	}

	public Sonda getById(Long id) throws Exception {
		Optional<Sonda> user = repository.findById(id);
		if ( user.isEmpty() )
			throw new Exception("Sonda not available");
		return user.get();
	}

	public void delete(Long id) throws Exception {
		Optional<Sonda> u = repository.findById(id);
		if (u.isPresent()) {
			repository.delete(u.get());
		} else {
			throw new Exception("Sonda non trovato");
		}
	}

	public void update(Sonda u) {
		repository.save(u);
	}

	public Sonda findByUsername(String username) throws Exception {
		Optional<Sonda> user = repository.findByUsername(username);
		if ( user.isEmpty() )
			throw new Exception("No user with that username found");
		return user.get();
	}

	public Page<Sonda> getAllPaginate(Pageable p) {
		return repository.findAll(p);
	}
}