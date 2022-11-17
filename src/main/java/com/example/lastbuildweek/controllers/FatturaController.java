package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.Fattura;
import com.example.lastbuildweek.services.ClienteService;
import com.example.lastbuildweek.services.FatturaService;
import com.example.lastbuildweek.utils.ConverDate;
import com.example.lastbuildweek.utils.FatturaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // RITORNA UNA LISTA DI FATTURE FILTRATE PER CLIENTE ID(PK)
    @GetMapping("/cliente/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Fattura>> get( @PathVariable("id") Long id, Pageable p ) throws Exception {

        return new ResponseEntity<>(
                fatturaService.filterByClienteId( id, p ),
                HttpStatus.OK
        );
    }

    // RITORNA UNA LISTA DI FATTURE FILTRATE PER STATO

    // RITORNA UNA LISTA DI FATTURE FILTRATE PER DATA(LOCALDATE)
    @GetMapping("/data/{data}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Fattura>> get( @PathVariable("data") String data, Pageable p ) throws Exception {

        return new ResponseEntity<>(
                fatturaService.filterFatturaByDataLocal( ConverDate.convertDate( data ), p ),
                HttpStatus.OK
        );
    }

    // RITORNA UNA LISTA DI FATTURE FILTRATE PER ANNO
    @GetMapping("/anno/{anno}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Fattura>> get( @PathVariable("anno") int anno, Pageable p ) throws Exception {

        return new ResponseEntity<>(
                fatturaService.filterFatturaByAnno( anno, p ),
                HttpStatus.OK
        );
    }

    // RITORNA UNA LISTA DI FATTURE FILTRATE PER RANGE DI IMPORTI
    @GetMapping("/range/inizio/{inizio}/fine/{fine}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Fattura>> get( @PathVariable("inizio") int inizio,
                                              @PathVariable("fine") int fine, Pageable p ) throws Exception {

        return new ResponseEntity<>(
                fatturaService.filterFatturaByRange( inizio, fine, p ),
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
