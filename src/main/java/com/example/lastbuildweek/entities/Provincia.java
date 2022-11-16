package com.example.lastbuildweek.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Provincia {

    private String nome;

    @Id
    @Column(nullable = false, unique = true)
    private String sigla;

    private String regione;

}
