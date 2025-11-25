package com.example.ProyectoOptativa.service;

import com.example.ProyectoOptativa.model.entity.Developer;
import com.example.ProyectoOptativa.model.repository.DeveloperRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    public Developer create(Developer dev) {
        return developerRepository.save(dev);
    }

    public boolean delete(Integer id) {

        if (!developerRepository.existsById(id)) {
            return false;
        }

        developerRepository.deleteById(id);
        return true;
    }
}
