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
    @Column(name = "comune_id", nullable = false)
    private Long comuneId;


    private String Nome;

    @ManyToOne
    @JoinColumn(name="provincia_id")
    @JsonManagedReference
    private Provincia provincia;



}
