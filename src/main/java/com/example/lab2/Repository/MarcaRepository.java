package com.example.lab2.Repository;

import com.example.lab2.Entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    Optional<Marca> findById(String id);
}