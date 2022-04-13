package com.example.lab2.Repository;

import com.example.lab2.Entity.Sede;
import com.example.lab2.Entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, String> {

    @Query(value = "select * from trabajadores where idsede = ?1", nativeQuery=true)
    List<Trabajador> findTrabajadorByIdsede(Sede idsede);

}