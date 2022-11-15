package com.example.java_venerdi_s7.repository;


import com.example.java_venerdi_s7.entities.Role;
import com.example.java_venerdi_s7.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleType( RoleType roleType);

}
