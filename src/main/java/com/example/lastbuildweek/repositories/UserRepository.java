package com.example.java_venerdi_s7.repository;

import com.example.java_venerdi_s7.entities.Sonda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SondaRepository extends JpaRepository<Sonda, Long> {

    Optional<Sonda> findByUsername( String username);


}
