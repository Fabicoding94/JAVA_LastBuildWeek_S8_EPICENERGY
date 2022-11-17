package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.Provincia;
import com.example.lastbuildweek.entities.Role;
import com.example.lastbuildweek.entities.RoleType;
import com.example.lastbuildweek.entities.User;
import com.example.lastbuildweek.services.ComuneService;
import com.example.lastbuildweek.services.ProvinciaService;
import com.example.lastbuildweek.services.RoleService;
import com.example.lastbuildweek.services.UserService;
import com.example.lastbuildweek.utils.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    //RITORNA TUTTI GLI UTENTI
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    public List<User> getAllUsers() {

        return userService.getAll();

    }

    //RITORNA TUTTI GLI UTENTI CON POSSIBILITA' DI PAGINAZIONE
    @GetMapping("/pageable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<User>> getAllUsersPageable( Pageable p ) {

        Page<User> findAll = userService.getAllPaginate( p );

        if( findAll.hasContent() ) {
            return new ResponseEntity<>( findAll, HttpStatus.OK );
        } else {
            return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
        }

    }

    // RITORNA UN SINGOLO USER PER ID(PK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getById( @PathVariable Long id ) throws Exception {

        return new ResponseEntity<>(
                userService.getById( id ),
                HttpStatus.OK
        );
    }

    //RITORNA TUTTI GLI UTENTI PER USERNAME(PK)
    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getByUsername( @PathVariable String username ) throws Exception {

        return new ResponseEntity<>(
                userService.findByUsername( username ),
                HttpStatus.OK
        );

    }

    // AGGIUNGI UN NUOVO UTENTE CON IL BODY COME RICHIESTA
    @PostMapping("/new-raw")
//    @PreAuthorize("hasRole('ADMIN')")
    public User create( @RequestBody User user ) {

        try {
            Set<Role> roles = new HashSet<>();
            roles.add( roleService.getById( 1L ) );
            user.setRoles( roles );

            userService.save( user );

            return user;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }

    // CREAZIONE ACCOUNT ADMIN
    @PostMapping("/new-raw-admin")
    public User createBasicAdmin( @RequestBody User user ) {

        try {
            Set<Role> roles = new HashSet<>();
            roles.add( roleService.getById( 1L ) );
            roles.add( roleService.getById( 2L ) );
            user.setRoles( roles );

            userService.save( user );

            return user;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }

    //AGGIUNGI UN NUOVO UTENTE CON LE PATHVARIABLE(POCO SICURO A MIO AVVISO)
    @PostMapping("/new-path/{nomeCompleto}/{username}/{password}/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public User create(
            @PathVariable("nomeCompleto") String nomeCompleto,
            @PathVariable("username") String username,
            @PathVariable("password") String password,
            @PathVariable("email") String email
    ) {

        try {

            User user = new User();
            user.setNomeCompleto( nomeCompleto );
            user.setUsername( username );
            user.setPassword( password );
            user.setEmail( email );
            user.setActive( true );

            Set<Role> rolesUser = new HashSet<>();
            rolesUser.add( roleService.getById( 1L ) );
            user.setRoles( rolesUser );

            userService.save( user );

            return user;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }

    //AGGIORNA LE PROPRIETA' DI UN UTENTE
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void update( @RequestBody User user ) {

        try {

            userService.save( user );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
    }

    // AGGIUNGI UN NUOVO RUOLO ALL'UTENTE
    @PutMapping("/{id}/add-role/{roleType}")
    @PreAuthorize("hasRole('ADMIN')")
    public void addRole(
            @PathVariable("id") Long id,
            @PathVariable("roleType") String roleType
    ) throws Exception {

        User u = userService.getById( id );

        if( roleType.equals( "ADMIN" ) ) {

            u.addRole( roleService.getByRole( RoleType.ROLE_ADMIN ) );

            userService.update( u );

        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable Long id ) {

        try {

            userService.delete( id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
