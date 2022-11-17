package com.example.lastbuildweek.repositories;

import com.example.lastbuildweek.entities.Cliente;
import com.example.lastbuildweek.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    @Query(
            value = "select p from Provincia p where p.sigla = :sigla"
    )
    Optional<Provincia> findBySigla( @PathVariable("sigla") String sigla );
}
