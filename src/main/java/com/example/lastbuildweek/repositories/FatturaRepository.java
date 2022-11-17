package com.example.lastbuildweek.repositories;

import com.example.lastbuildweek.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}
