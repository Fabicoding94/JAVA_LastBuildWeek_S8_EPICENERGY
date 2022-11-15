package com.example.lastbuildweek.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @OneToOne
    private Cliente cliente;

    private String nomeCompleto;

    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    public User(String nomeCompleto, String username, String password) {

        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

    private Boolean active = true;

    public void addRole(Role r) {
        this.roles.add(r);
    }



}
