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
    private long IndirizzoLegaleID;

    private String Via;
    private int Civico;
    private int Cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comuneId;



}
