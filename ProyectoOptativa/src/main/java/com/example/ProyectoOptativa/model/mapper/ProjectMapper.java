package com.example.ProyectoOptativa.model.mapper;

import com.example.ProyectoOptativa.model.dto.*;
import com.example.ProyectoOptativa.model.entity.Project;

import java.util.stream.Collectors;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project p) {

        ProjectDTO dto = new ProjectDTO();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setDemoUrl(p.getDemoUrl());
        dto.setPicture(p.getPicture());
        dto.setStartDate(p.getStartDate());
        dto.setEndDate(p.getEndDate());

        // Status
        if (p.getStatus() != null) {
            StatusDTO statusDTO = new StatusDTO();
            statusDTO.setId(p.getStatus().getId());
            statusDTO.setName(p.getStatus().getName());
            dto.setStatus(statusDTO);
        }

        // Technologies
        dto.setTechnologies(
            p.getTechnologies().stream().map(t -> {
                TechnologyDTO td = new TechnologyDTO();
                td.setId(t.getId());
                td.setName(t.getName());
                return td;
            }).collect(Collectors.toList())
        );

        // Developers
        dto.setDevelopers(
            p.getDevelopers().stream().map(d -> {
                DeveloperDTO dd = new DeveloperDTO();
                dd.setId(d.getId());
                dd.setName(d.getName());
                dd.setSurname(d.getSurname());
                dd.setEmail(d.getEmail());
                dd.setLinkedinUrl(d.getLinkedinUrl());
                dd.setGithubUrl(d.getGithubUrl());
                return dd;
            }).collect(Collectors.toList())
        );

        return dto;
    }
}
