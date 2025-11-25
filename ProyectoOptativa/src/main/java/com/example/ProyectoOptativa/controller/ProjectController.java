package com.example.ProyectoOptativa.controller;

import com.example.ProyectoOptativa.model.entity.Project;
import com.example.ProyectoOptativa.service.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping(value = "/{word}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> search(@PathVariable String word) {
        List<Project> list = projectService.searchByWord(word);

        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project created = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Project> update(@PathVariable Integer id, @RequestBody Project project) {

        Project updated = projectService.updateProject(id, project);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        boolean deleted = projectService.delete(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}
