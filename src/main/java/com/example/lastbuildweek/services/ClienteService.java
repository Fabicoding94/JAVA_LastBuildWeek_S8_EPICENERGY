package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.Cliente;
import com.example.lastbuildweek.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    };


    public Cliente getById(Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if ( cliente.isEmpty() )
            throw new Exception("Cliente not available");
        return cliente.get();
    }

    public void delete(Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
        } else {
            throw new Exception("Cliente non trovato");
        }
    }

    public void update(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Page<Cliente> getAllPaginate(Pageable p) {
        return clienteRepository.findAll(p);
    }

    public Page<Cliente> getByNomeContatto(Pageable p) {
        return clienteRepository.findByNomeContatto(p);
    }


    public Page<Cliente> getByFatturatoAnnuo(Pageable p) {
        return clienteRepository.findByFatturatoAnnuo(p);
    }


    public Page<Cliente> getByDataInserimento(Pageable p) {
        return clienteRepository.findByDataInserimento(p);
    }

    public Page<Cliente> getByDataUltimoContatto(Pageable p) {
        return clienteRepository.findByDataUltimoContatto(p);
    }

    public Page<Cliente> getByNomeProvincia(Pageable p) {
        return clienteRepository.findByNomeProvincia(p);
    }

    ////////////////////////////////////////////////////////////////

    public Page<Cliente> filterByFatturato( int fatturato, Pageable p) {
        return clienteRepository.filterByFatturatoAnnuo(fatturato, p);
    }

    public Page<Cliente> filterByDataInserimento(LocalDate dataInserimento, Pageable p) {
        return clienteRepository.filterByDataInserimento( dataInserimento, p );
    }

    public Page<Cliente> filterByDataUltimoContatto(LocalDate data, Pageable p) {
        return clienteRepository.filterByDataUltimoContatto( data, p );
    }

    public Page<Cliente> filterByNomeECognome(String nome, String cognome, Pageable p) {
        return clienteRepository.filterByNomeECognome( nome, cognome, p );
    }


}
