package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.Fattura;
import com.example.lastbuildweek.services.ClienteService;
import com.example.lastbuildweek.services.FatturaService;
import com.example.lastbuildweek.utils.FatturaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/fatture")
@Slf4j
@CrossOrigin(origins = "*")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private ClienteService clienteService;


    // RITORNA UNA SINGOLA FATTURA PER ID(PK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Fattura> get(@PathVariable("id") Long id ) throws Exception {

        return new ResponseEntity<>(
                fatturaService.getById( id ),
                HttpStatus.OK
        );
    }


    // AGGIUNGI UNA NUOVA FATTURA CON IL BODY COME RICHIESTA
    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public Fattura create( @RequestBody FatturaRequest fatturaRequest ) {

        try {

            Fattura fattura = Fattura.builder()
                    .anno(fatturaRequest.getAnno())
                    .importo(fatturaRequest.getImporto())
                    .cliente(clienteService.getById(fatturaRequest.getClienteId()))
                    .data(LocalDate.now())
                    .build();

            fatturaService.save( fattura );

            return fattura;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }




    //AGGIORNA LE PROPRIETA' DI UNA FATTURA
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void update( @RequestBody Fattura fattura ) {

        try {

            fatturaService.save( fattura );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
    }

    //ELIMINA UNA FATTURA PER id
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable("id") Long id ) {

        try {

            fatturaService.delete( id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
