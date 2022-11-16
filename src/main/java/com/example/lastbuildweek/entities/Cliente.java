package com.example.lastbuildweek.entities;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partitaIva", nullable = false)
    private Long partitaIva;

    @OneToOne
    private User user;

}
