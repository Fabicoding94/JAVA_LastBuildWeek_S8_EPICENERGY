package com.example.lastbuildweek.repositories;

import com.example.lastbuildweek.entities.IndirizzoLegale;
import com.example.lastbuildweek.entities.IndirizzoOperativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoOperativoRepository extends JpaRepository<IndirizzoOperativo, Long> {
}
