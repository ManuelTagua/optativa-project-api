package com.example.ProyectoOptativa.model.repository;

import com.example.ProyectoOptativa.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
}
