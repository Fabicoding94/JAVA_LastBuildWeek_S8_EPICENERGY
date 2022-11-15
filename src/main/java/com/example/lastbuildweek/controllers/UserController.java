package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.Role;
import com.example.lastbuildweek.entities.RoleType;
import com.example.lastbuildweek.entities.User;
import com.example.lastbuildweek.services.RoleService;
import com.example.lastbuildweek.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    @CrossOrigin
    public List<User> getAllSonde() {
        return userService.getAll();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<User>> getAllPageable( Pageable p) {
        Page<User> findAll = userService.getAllPaginate(p);

        if (findAll.hasContent()) {
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) throws Exception {

        return new ResponseEntity<>( userService.getById(id), HttpStatus.OK);

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) throws Exception {
        return new ResponseEntity<>( userService.findByUsername(username), HttpStatus.OK);

    }

    @PostMapping("/new-raw")
    public User create(@RequestBody User user) {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getById( 1L ));
            user.setRoles(roles);
            userService.save(user);

            return user;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping("/new/{nomeCompleto}/{username}/{password}/{email}")
    public User create(
            @PathVariable("nomeCompleto") String nomeCompleto,
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @PathVariable("email") String email
    ) {
        try {
            User user = new User();
            user.setNomeCompleto(nomeCompleto);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail( email );
            user.setActive( true );
            Set<Role> rolesUser = new HashSet<>();
            rolesUser.add( roleService.getById( 1L ) );
            user.setRoles( rolesUser );


            userService.save(user);

            return user;
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;

    }

    @PutMapping("")
    public void update(@RequestBody User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PutMapping("/{id}/add-role/{roleType}")
    public void addRole(@PathVariable("id") Long id, @PathVariable("roleType") RoleType roleType) throws Exception {
        User u = userService.getById(id);
        u.addRole( roleService.getByRole(roleType));

        userService.update(u);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
