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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provincia", nullable = false)

    private long ProvinciaID;
    private String Nome;
    private String Sigla;

    @ToString.Exclude
    @OneToMany(mappedBy="provincia", cascade=CascadeType.ALL)
    @JsonBackReference
    private List<Comune> comuni;
}
