package com.example.java_venerdi_s7.controller;


import com.example.java_venerdi_s7.entities.Role;
import com.example.java_venerdi_s7.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService rs;
	
	@GetMapping("")
	public List<Role> getAllUsers() {
		return rs.getAll();
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Role>> getAllPageable(Pageable p) {
		Page<Role> findAll = rs.getAllPaginate(p);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> readById(@PathVariable Long id) throws Exception {
			return new ResponseEntity<>(rs.getById(id), HttpStatus.OK);			

	}
	
	@PostMapping("/new")
	public void create(@RequestBody Role role) {
			rs.save(role);

	}

	@PutMapping("")
	public void update(@RequestBody Role role) {
		try {
			rs.save(role);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteById(@PathVariable Long id) {
		try {
			rs.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}