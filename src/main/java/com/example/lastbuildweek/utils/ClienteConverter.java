package com.example.lastbuildweek.utils;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClienteConverter {

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
