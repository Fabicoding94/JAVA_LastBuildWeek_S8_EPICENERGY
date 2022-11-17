package com.example.lastbuildweek.repositories;

import com.example.lastbuildweek.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //QUERIES DI ORDINAMENTO
    @Query(
         value="select c from Cliente c order by c.nomeContatto asc"
    )
    Page<Cliente> findByNomeContatto(Pageable pageable);
    @Query(
            value="select c from Cliente c order by c.fatturatoAnnuo asc"
    )
    Page<Cliente> findByFatturatoAnnuo( Pageable pageable);

    @Query("select c from Cliente c order by c.dataInserimento asc"
    )
    Page<Cliente> findByDataInserimento( Pageable pageable);

    @Query("select c from Cliente c order by c.dataUltimoContatto asc"
    )
    Page<Cliente> findByDataUltimoContatto( Pageable pageable);

    @Query("select c from Cliente c order by c.indirizzoLegale.comune.nomeProvincia asc"
    )
    Page<Cliente> findByNomeProvincia( Pageable pageable);

    //---------------------------------FINE--------------------------------------------//

    //QUERIES DI FILTRAGGIO
    @Query("select c from Cliente c where c.fatturatoAnnuo<=:param"
    )
    Page<Cliente> filterByFatturatoAnnuo(@Param("param" ) int fatturato, Pageable pageable);

    @Query("select c from Cliente c where c.dataInserimento=:param"
    )
    Page<Cliente> filterByDataInserimento(@Param("param" ) int dataInserimento, Pageable pageable);



}
