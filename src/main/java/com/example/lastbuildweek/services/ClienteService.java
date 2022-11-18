package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.Cliente;
import com.example.lastbuildweek.entities.RagioneSociale;
import com.example.lastbuildweek.entities.User;
import com.example.lastbuildweek.repositories.ClienteRepository;
import com.example.lastbuildweek.utils.ClienteConverter;
import com.example.lastbuildweek.utils.RagioneSocialeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IndirizzoLegaleService indirizzoLegaleService;

    @Autowired
    private IndirizzoOperativoService indirizzoOperativoService;

    public void save( Cliente cliente ) {
        clienteRepository.save( cliente );
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente createAndUpdate( ClienteConverter clienteConverter, int clienteId ) throws Exception {
        Optional<Cliente> clienteFind = clienteRepository.findById( ( long ) clienteId );
        if( clienteFind.isPresent() ) {
            Cliente cliente = Cliente.builder()
                    .clienteId( clienteFind.get().getClienteId() )
                    .partitaIva( clienteConverter.getPartitaIva() == 0 ? clienteFind.get().getPartitaIva() : clienteConverter.getPartitaIva() )
                    .user( clienteConverter.getUserId() == 0 ? clienteFind.get().getUser() : userService.getById( ( long ) clienteConverter.getUserId() ) )
                    .indirizzoLegale( clienteConverter.getIndirizzoLegaleId() == 0 ? clienteFind.get().getIndirizzoLegale() : indirizzoLegaleService.getById( ( long ) clienteConverter.getIndirizzoLegaleId() ) )
                    .indirizzoOperativo( clienteConverter.getIndirizzoOperativoId() == 0 ?
                            clienteFind.get().getIndirizzoOperativo() : indirizzoOperativoService.getById( ( long ) clienteConverter.getIndirizzoOperativoId() ) )
                    .email( clienteConverter.getEmail() == null ? clienteFind.get().getEmail() : clienteConverter.getEmail() )
                    .pec( clienteConverter.getPec() == null ? clienteFind.get().getPec() : clienteConverter.getPec() )
                    .emailContatto( clienteConverter.getEmailContatto() == null ? clienteFind.get().getEmailContatto() : clienteConverter.getEmailContatto() )
                    .nomeContatto( clienteConverter.getNomeContatto() == null ? clienteFind.get().getNomeContatto() : clienteConverter.getNomeContatto() )
                    .cognomeContatto( clienteConverter.getCognomeContatto() == null ? clienteFind.get().getCognomeContatto() : clienteConverter.getCognomeContatto() )
                    .telefonoContatto( clienteConverter.getTelefonoContatto() == null ?
                            clienteFind.get().getTelefonoContatto() : clienteConverter.getTelefonoContatto() )
                    .ragioneSociale( clienteConverter.getRagioneSociale() == null ? clienteFind.get().getRagioneSociale() : RagioneSocialeParser.parse( clienteConverter.getRagioneSociale() ) )
                    .fatturatoAnnuo( clienteConverter.getFatturatoAnnuo() == 0 ? clienteFind.get().getFatturatoAnnuo() : clienteConverter.getFatturatoAnnuo() )
                    .dataInserimento( clienteFind.get().getDataInserimento() )
                    .dataUltimoContatto( clienteFind.get().getDataUltimoContatto() )
                .build();
            clienteRepository.save( cliente );
            return cliente;
        } else {
            throw new Exception( "Cliente not available" );
        }

    }

    ;

    public Cliente createAndSave( ClienteConverter clienteConverter ) throws Exception {

        Cliente cliente = Cliente.builder()
                .partitaIva( clienteConverter.getPartitaIva() )
                .user( userService.getById( ( long ) clienteConverter.getUserId() ) )
                .indirizzoLegale( indirizzoLegaleService.getById( ( long ) clienteConverter.getIndirizzoLegaleId() ) )
                .indirizzoOperativo( indirizzoOperativoService.getById( ( long ) clienteConverter.getIndirizzoOperativoId() ) )
                .email( clienteConverter.getEmail() )
                .pec( clienteConverter.getPec() )
                .emailContatto( clienteConverter.getEmailContatto() )
                .nomeContatto( clienteConverter.getNomeContatto() )
                .cognomeContatto( clienteConverter.getCognomeContatto() )
                .telefonoContatto( clienteConverter.getTelefonoContatto() )
                .ragioneSociale( RagioneSocialeParser.parse( clienteConverter.getRagioneSociale() ) )
                .fatturatoAnnuo( clienteConverter.getFatturatoAnnuo() )
                .dataInserimento( LocalDate.now() )
                .dataUltimoContatto( LocalDate.now() )
                .build();

        return clienteRepository.save( cliente );
    }

    ;


    public Cliente getById( Long id ) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById( id );
        if( cliente.isEmpty() )
            throw new Exception( "Cliente not available" );
        return cliente.get();
    }

    public void delete( Long id ) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById( id );
        if( cliente.isPresent() ) {
            clienteRepository.delete( cliente.get() );
        } else {
            throw new Exception( "Cliente non trovato" );
        }
    }

    public void update( Cliente cliente ) {
        clienteRepository.save( cliente );
    }

    public Page<Cliente> getAllPaginate( Pageable p ) {
        return clienteRepository.findAll( p );
    }

    public Page<Cliente> getByNomeContatto( Pageable p ) {
        return clienteRepository.findByNomeContatto( p );
    }


    public Page<Cliente> getByFatturatoAnnuo( Pageable p ) {
        return clienteRepository.findByFatturatoAnnuo( p );
    }


    public Page<Cliente> getByDataInserimento( Pageable p ) {
        return clienteRepository.findByDataInserimento( p );
    }

    public Page<Cliente> getByDataUltimoContatto( Pageable p ) {
        return clienteRepository.findByDataUltimoContatto( p );
    }

    public Page<Cliente> getByNomeProvincia( Pageable p ) {
        return clienteRepository.findByNomeProvincia( p );
    }

    ////////////////////////////////////////////////////////////////

    public Page<Cliente> filterByFatturato( int fatturato, Pageable p ) {
        return clienteRepository.filterByFatturatoAnnuo( fatturato, p );
    }

    public Page<Cliente> filterByDataInserimento( LocalDate dataInserimento, Pageable p ) {
        return clienteRepository.filterByDataInserimento( dataInserimento, p );
    }

    public Page<Cliente> filterByDataUltimoContatto( LocalDate data, Pageable p ) {
        return clienteRepository.filterByDataUltimoContatto( data, p );
    }

    public Page<Cliente> filterByNomeECognome( String nome, String cognome, Pageable p ) {
        return clienteRepository.filterByNomeECognome( nome, cognome, p );
    }


}
