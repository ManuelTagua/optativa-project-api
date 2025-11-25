package com.example.ProyectoOptativa.controller;

import com.example.ProyectoOptativa.model.entity.Status;
import com.example.ProyectoOptativa.model.repository.StatusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        return ResponseEntity.ok(statusList);
    }
}
