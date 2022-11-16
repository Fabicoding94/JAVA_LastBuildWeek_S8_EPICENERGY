//package com.example.lastbuildweek.controllers;
//
//import com.example.lastbuildweek.entities.Cliente;
//import com.example.lastbuildweek.entities.Role;
//import com.example.lastbuildweek.entities.RoleType;
//import com.example.lastbuildweek.entities.User;
//import com.example.lastbuildweek.services.ClienteService;
//import com.example.lastbuildweek.services.RoleService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/api/clienti")
//@Slf4j
//@CrossOrigin(origins = "*")
//public class ClienteController {
//    @Autowired
//    private ClienteService clienteService;
//
//    @Autowired
//    private RoleService roleService;
//
//
//    // RITORNA UN SINGOLO USER PER ID(PK)
//    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Cliente> getById(@PathVariable Long id ) throws Exception {
//
//        return new ResponseEntity<>(
//                clienteService.getById( id ),
//                HttpStatus.OK
//        );
//    }
//
//
//    // AGGIUNGI UN NUOVO UTENTE CON IL BODY COME RICHIESTA
//    @PostMapping("/new-raw")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Cliente create( @RequestBody Cliente cliente ) {
//
//        try {
//
//            clienteService.save( cliente );
//
//            return cliente;
//
//        } catch( Exception e ) {
//
//            log.error( e.getMessage() );
//
//        }
//
//        return null;
//
//    }
//
//
//    //AGGIUNGI UN NUOVO UTENTE CON LE PATHVARIABLE(POCO SICURO A MIO AVVISO)
//    @PostMapping("/new-path/{nomeContatto}/{cognomeContatto}/{password}/{email}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public User create(
//            @PathVariable("nomeCompleto") String nomeCompleto,
//            @PathVariable("username") String username,
//            @PathVariable("password") String password,
//            @PathVariable("email") String email
//    ) {
//
//        try {
//
//            User user = new User();
//            user.setNomeCompleto( nomeCompleto );
//            user.setUsername( username );
//            user.setPassword( password );
//            user.setEmail( email );
//            user.setActive( true );
//
//            Set<Role> rolesUser = new HashSet<>();
//            rolesUser.add( roleService.getById( 1L ) );
//            user.setRoles( rolesUser );
//
//            clienteService.save( user );
//
//            return user;
//
//        } catch( Exception e ) {
//
//            log.error( e.getMessage() );
//
//        }
//
//        return null;
//
//    }
//
//    //AGGIORNA LE PROPRIETA' DI UN UTENTE
//    @PutMapping("")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void update( @RequestBody User user ) {
//
//        try {
//
//            clienteService.save( user );
//
//        } catch( Exception e ) {
//
//            log.error( e.getMessage() );
//
//        }
//    }
//
//    // AGGIUNGI UN NUOVO RUOLO ALL'UTENTE
//    @PutMapping("/{id}/add-role/{roleType}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void addRole(
//            @PathVariable("id") Long id,
//            @PathVariable("roleType") String roleType
//    ) throws Exception {
//
//        User u = clienteService.getById( id );
//
//        if( roleType.equals( "ADMIN" ) ) {
//
//            u.addRole( roleService.getByRole( RoleType.ROLE_ADMIN ) );
//
//            clienteService.update( u );
//
//        }
//
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void deleteById( @PathVariable Long id ) {
//
//        try {
//
//            clienteService.delete( id );
//
//        } catch( Exception e ) {
//
//            log.error( e.getMessage() );
//
//        }
//
//    }
//}
