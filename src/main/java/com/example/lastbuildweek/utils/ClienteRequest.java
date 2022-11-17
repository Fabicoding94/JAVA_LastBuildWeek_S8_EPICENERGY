package com.example.lastbuildweek.utils;

import com.example.lastbuildweek.entities.RagioneSociale;
import lombok.*;

import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClienteRequest {

    private int partitaIva;
    private int userId;
    private int indirizzoLegaleId;
    private int indirizzoOperativoId;
    private String email;
    private String pec;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private Long telefonoContatto;
    private String ragioneSociale;
    private int fatturatoAnnuo;
}
