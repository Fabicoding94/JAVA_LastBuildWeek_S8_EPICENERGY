package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.Role;
import com.example.lastbuildweek.entities.RoleType;
import com.example.lastbuildweek.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
	public List<Role> create() {
		Role roleUser = new Role();
		Role roleAdmin = new Role();
		roleUser.setRoleType( RoleType.ROLE_USER );
		roleAdmin.setRoleType( RoleType.ROLE_ADMIN );

		rs.save(roleUser);
		rs.save(roleAdmin);

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleUser);
        roles.add(roleAdmin);
		return roles;
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