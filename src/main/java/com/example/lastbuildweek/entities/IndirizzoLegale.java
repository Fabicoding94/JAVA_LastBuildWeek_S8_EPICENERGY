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
public class IndirizzoLegale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indirizzoLegale", nullable = false)
    private Long indirizzoLegaleId;

    private String via;
    private int civico;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comuneId;



}
