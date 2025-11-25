package com.example.ProyectoOptativa.controller;

import com.example.ProyectoOptativa.model.dto.DeveloperDTO;
import com.example.ProyectoOptativa.model.entity.Developer;
import com.example.ProyectoOptativa.model.mapper.DeveloperMapper;
import com.example.ProyectoOptativa.service.DeveloperService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getAll() {

        List<DeveloperDTO> list = developerService.findAll()
                .stream()
                .map(DeveloperMapper::toDTO)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<DeveloperDTO> create(@RequestBody Developer dev) {

        Developer created = developerService.create(dev);

        DeveloperDTO dto = DeveloperMapper.toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        boolean deleted = developerService.delete(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}
