package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.IndirizzoLegale;
import com.example.lastbuildweek.entities.IndirizzoOperativo;
import com.example.lastbuildweek.repositories.IndirizzoOperativoRepository;
import com.example.lastbuildweek.services.ComuneService;
import com.example.lastbuildweek.services.IndirizzoOperativoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/indirizzo-operativo")
@Slf4j
@CrossOrigin(origins = "*")
public class IndirizzoOperativoController {

    @Autowired
    private IndirizzoOperativoService indirizzoOperativoService;

    @Autowired
    private ComuneService comuneService;

    @Autowired
    private IndirizzoOperativoRepository indirizzoOperativoRepository;


    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<IndirizzoOperativo>> get() throws Exception {

        return new ResponseEntity<>(
                indirizzoOperativoRepository.findAll( ),
                HttpStatus.OK
        );
    }

    // RITORNA UN SINGOLO INDIRIZZO-OPERATIVO PER ID(PK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<IndirizzoOperativo> get(@PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                indirizzoOperativoService.getById( id ),
                HttpStatus.OK
        );
    }


    // AGGIUNGI UN NUOVO INDIRIZZO-OPERATIVO CON IL BODY COME RICHIESTA
    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public IndirizzoOperativo create(
            @RequestParam("via") String via,
            @RequestParam("civico") Integer civico,
            @RequestParam("cap") Integer cap,
            @RequestParam("nomeComune") String nomeComune


    ) {

        try {

            IndirizzoOperativo indirizzoOperativo = IndirizzoOperativo.builder()
                    .via(via)
                    .civico(civico)
                    .cap(cap)
                    .comune(comuneService.getByNome(nomeComune))
                    .build();

            indirizzoOperativoService.save( indirizzoOperativo );

            return indirizzoOperativo;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }




    //AGGIORNA LE PROPRIETA' DI UN INDIRIZZO-OPERATIVO
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void update( @RequestBody IndirizzoOperativo indirizzoOperativo ) {

        try {

            indirizzoOperativoService.save( indirizzoOperativo );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable("id") Long id ) {

        try {

            indirizzoOperativoService.delete( id);

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
