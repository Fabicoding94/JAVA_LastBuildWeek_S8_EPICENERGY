package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.IndirizzoLegale;
import com.example.lastbuildweek.services.ComuneService;
import com.example.lastbuildweek.services.IndirizzoLegaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/indirizzo-legale")
@Slf4j
@CrossOrigin(origins = "*")
public class IndirizzoLegaleController {

    @Autowired
    private IndirizzoLegaleService indirizzoLegaleService;

    @Autowired
    private ComuneService comuneService;


    // RITORNA UN SINGOLO INDIRIZZO-LEGALE PER ID(PK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<IndirizzoLegale> get(@PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                indirizzoLegaleService.getById( id ),
                HttpStatus.OK
        );
    }


    // AGGIUNGI UN NUOVO INDIRIZZO-LEGALE CON IL BODY COME RICHIESTA
    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public IndirizzoLegale create(
            @RequestParam("via") String via,
            @RequestParam("civico") Integer civico,
            @RequestParam("cap") Integer cap,
            @RequestParam("nomeComune") String nomeComune


    ) {

        try {

            IndirizzoLegale indirizzoLegale = IndirizzoLegale.builder()
                    .via(via)
                    .civico(civico)
                    .cap(cap)
                    .comune(comuneService.getByNome(nomeComune))
                    .build();

            indirizzoLegaleService.save( indirizzoLegale );

            return indirizzoLegale;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }




    //AGGIORNA LE PROPRIETA' DI UN INDIRIZZO-LEGALE
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void update( @RequestBody IndirizzoLegale indirizzoLegale ) {

        try {

            indirizzoLegaleService.save( indirizzoLegale );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable("id") Long id ) {

        try {

            indirizzoLegaleService.delete( id);

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
