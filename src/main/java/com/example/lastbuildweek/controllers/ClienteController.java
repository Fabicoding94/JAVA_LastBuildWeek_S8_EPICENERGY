package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.*;
import com.example.lastbuildweek.services.*;
import com.example.lastbuildweek.utils.ClienteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/clienti")
@Slf4j
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndirizzoLegaleService indirizzoLegaleService;

    @Autowired
    private IndirizzoOperativoService indirizzoOperativoService;



    // RITORNA UN SINGOLO CLIENTE PER ID(PK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cliente> getById(@PathVariable Long id ) throws Exception {

        return new ResponseEntity<>(
                clienteService.getById( id ),
                HttpStatus.OK
        );
    }

    //RITORNA UNA PAGINAZIONE DI TUTTI I CLIENTI ORDINATI PER NOME

    @GetMapping("/nome/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Cliente>> getByNomeContatto(Pageable p) {
        return new ResponseEntity<>(
                clienteService.getByNomeContatto( p ),
                HttpStatus.OK
        );
    }


    //RITORNA UNA PAGINAZIONE DI TUTTI I CLIENTI ORDINATI PER FATTURATO ANNUO

    @GetMapping("/fatturato-annuo/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Cliente>> getByFatturatoAnnuo(Pageable p) {
        return new ResponseEntity<>(
                clienteService.getByFatturatoAnnuo( p ),
                HttpStatus.OK
        );
    }
    //RITORNA UNA PAGINAZIONE DI TUTTI I CLIENTI ORDINATI PER FATTURATO ANNUO
    @GetMapping("/data-inserimento/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Cliente>> getByDataInserimento(Pageable p) {
        return new ResponseEntity<>(
                clienteService.getByDataInserimento( p ),
                HttpStatus.OK
        );
    }

    @GetMapping("/data-ultimo-contatto/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Cliente>> getByDataUltimoContatto(Pageable p) {
        return new ResponseEntity<>(
                clienteService.getByDataUltimoContatto( p ),
                HttpStatus.OK
        );
    }

    //RITORNA UNA LISTA DI CLIENTI ORDINATI PER PROVINCIA
    @GetMapping("/provincia/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Cliente>> getByNomeProvincia(Pageable p) {
        return new ResponseEntity<>(
                clienteService.getByNomeProvincia( p ),
                HttpStatus.OK
        );
    }

    //RITORNA UNA LISTA DI CLIENTI FILTRATI PER FATTURATO ANNUO < DI UN PARAMETRO DATO
    @GetMapping("/fatturato/{fatturato}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Cliente>> getByFatturato(@PathVariable("fatturato") int fatturato, Pageable p) {
        return new ResponseEntity<>(
                clienteService.getByFatturato(fatturato, p ),
                HttpStatus.OK
        );
    }

//    //RITORNA UNA LISTA DI CLIENTI FILTRATI PER DATA INSERIMENTO
//    @GetMapping("/fatturato/{fatturato}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Page<Cliente>> getByFatturato(@PathVariable("fatturato") int fatturato, Pageable p) {
//        return new ResponseEntity<>(
//                clienteService.getByFatturato(fatturato, p ),
//                HttpStatus.OK
//        );
//    }






    // AGGIUNGI UN NUOVO CLIENTE CON IL BODY COME RICHIESTA
    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public Cliente create( @RequestBody ClienteRequest clienteRequest ) {


        try {
            Cliente cliente = Cliente.builder()
                    .partitaIva(clienteRequest.getPartitaIva())
                    .user(userService.getById((long) clienteRequest.getUserId()))
                    .indirizzoLegale(indirizzoLegaleService.getById((long) clienteRequest.getIndirizzoLegaleId()))
                    .indirizzoOperativo(indirizzoOperativoService.getById((long) clienteRequest.getIndirizzoOperativoId()))
                    .email(clienteRequest.getEmail())
                    .pec(clienteRequest.getPec())
                    .emailContatto(clienteRequest.getEmailContatto())
                    .nomeContatto(clienteRequest.getNomeContatto())
                    .cognomeContatto(clienteRequest.getCognomeContatto())
                    .telefonoContatto(clienteRequest.getTelefonoContatto())
                    .ragioneSociale(this.parser(clienteRequest.getRagioneSociale()))
                    .fatturatoAnnuo(clienteRequest.getFatturatoAnnuo())
                    .dataInserimento(LocalDate.now())
                    .dataUltimoContatto(LocalDate.now())
                    .build();



            clienteService.save( cliente );

            return cliente;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }

    public RagioneSociale parser(String stringa){
        switch(stringa){
            case "PA" -> {
                return RagioneSociale.PA;
            }
            case "SAS" -> {
                return RagioneSociale.SAS;
            }
            case "SPA" -> {
                return RagioneSociale.SPA;
            }
            case "SRL" -> {
                return RagioneSociale.SRL;
            }

        }
        return RagioneSociale.PA;

    }




    //AGGIORNA LE PROPRIETA' DI UN CLIENTE
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void update( @RequestBody Cliente cliente ) {

        try {

            clienteService.save( cliente );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable Long id ) {

        try {

            clienteService.delete( id );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
