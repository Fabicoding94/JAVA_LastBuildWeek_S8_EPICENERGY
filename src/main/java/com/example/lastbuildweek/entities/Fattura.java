package com.example.lastbuildweek.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero", nullable = false)
    private Long numero;

    private int anno;

    private LocalDate data;

    private int importo;

    @ManyToOne
    @JoinColumn(name = "cliente_partita_iva")
    private Cliente cliente;

}
