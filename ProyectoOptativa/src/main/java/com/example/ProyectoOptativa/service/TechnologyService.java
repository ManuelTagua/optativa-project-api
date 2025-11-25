package com.example.ProyectoOptativa.service;

import com.example.ProyectoOptativa.model.entity.Technology;
import com.example.ProyectoOptativa.model.repository.TechnologyRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    public Technology create(Technology tech) {
        return technologyRepository.save(tech);
    }

    public boolean delete(Integer id) {

        if (!technologyRepository.existsById(id)) {
            return false;
        }

        technologyRepository.deleteById(id);
        return true;
    }
}
