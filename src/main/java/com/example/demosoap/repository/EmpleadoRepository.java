package com.example.demosoap.repository;


import com.example.demosoap.model.EmpleadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer> {
}