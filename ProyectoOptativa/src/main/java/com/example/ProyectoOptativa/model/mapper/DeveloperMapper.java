package com.example.ProyectoOptativa.model.mapper;

import com.example.ProyectoOptativa.model.dto.DeveloperDTO;
import com.example.ProyectoOptativa.model.entity.Developer;

public class DeveloperMapper {

    public static DeveloperDTO toDTO(Developer d) {

        DeveloperDTO dto = new DeveloperDTO();

        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setSurname(d.getSurname());
        dto.setEmail(d.getEmail());
        dto.setLinkedinUrl(d.getLinkedinUrl());
        dto.setGithubUrl(d.getGithubUrl());

        return dto;
    }
}
