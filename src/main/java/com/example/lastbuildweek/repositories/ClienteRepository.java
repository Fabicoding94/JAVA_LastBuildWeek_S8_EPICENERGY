package com.example.lastbuildweek.repositories;

import com.example.lastbuildweek.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(
         value="select c from Cliente c order by c.nomeContatto asc"
    )
    Page<Cliente> findByNomeContatto(Pageable pageable);

//    @Query(
//            value="select c from Cliente c order by c.fatturatoAnnuo asc"
//    )
//    List<Cliente> findByFatturatoAnnuo( );
}
