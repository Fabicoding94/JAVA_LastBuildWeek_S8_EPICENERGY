package com.example.java_venerdi_s7.services;

import com.example.java_venerdi_s7.entities.Role;
import com.example.java_venerdi_s7.entities.RoleType;
import com.example.java_venerdi_s7.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

	@Autowired
	RoleRepository repository;

	public Role save( Role x) {
		return repository.save(x);
	}
	
	public Page<Role> getAllPaginate(Pageable p) {
		return repository.findAll(p);
	}

	public List<Role> getAll() {
		return repository.findAll();
	}

	public Role getById(Long id) throws Exception {
		Optional<Role> ba = repository.findById(id);
		if ( ba.isEmpty() )
			throw new Exception("Role not available");
		return ba.get();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Role getByRole( RoleType roleType) throws Exception {
		Optional<Role> ba = repository.findByRoleType(roleType);
		if ( ba.isEmpty() )
			throw new Exception("Role not available");
		return ba.get();
	}
}
