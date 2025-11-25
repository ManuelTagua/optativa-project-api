package com.example.ProyectoOptativa.controller;

import com.example.ProyectoOptativa.model.dto.TechnologyDTO;
import com.example.ProyectoOptativa.model.entity.Technology;
import com.example.ProyectoOptativa.model.mapper.TechnologyMapper;
import com.example.ProyectoOptativa.service.TechnologyService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public ResponseEntity<List<TechnologyDTO>> getAll() {

        List<TechnologyDTO> list = technologyService.findAll()
                .stream()
                .map(TechnologyMapper::toDTO)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<TechnologyDTO> create(@RequestBody Technology tech) {

        Technology created = technologyService.create(tech);
        TechnologyDTO dto = TechnologyMapper.toDTO(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        boolean removed = technologyService.delete(id);

        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}
