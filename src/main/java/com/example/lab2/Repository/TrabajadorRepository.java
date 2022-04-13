package com.example.lab2.Repository;

import com.example.lab2.Entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRepository extends JpaRepository<Trabajador, String> {
}