package com.example.lastbuildweek.repositories;

import com.example.lastbuildweek.entities.Cliente;
import com.example.lastbuildweek.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
}
