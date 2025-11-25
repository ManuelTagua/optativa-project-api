package com.example.ProyectoOptativa.model.repository;

import com.example.ProyectoOptativa.model.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {}