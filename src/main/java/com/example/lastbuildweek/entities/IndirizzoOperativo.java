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
public class IndirizzoOperativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indirizzoOperativo", nullable = false)
    private Long indirizzoOperativoID;

    private String Via;
    private int civico;
    private int cap;


    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comuneId;
}
