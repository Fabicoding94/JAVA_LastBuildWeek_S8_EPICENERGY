package com.example.lastbuildweek.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comuneId;

    private String nome;

//    @ManyToOne
//    @JoinColumn(name = "provincia_id")
//    private Provincia provincia;
    private String nomeProvincia;
}
