package com.example.ProyectoOptativa.model.mapper;

import com.example.ProyectoOptativa.model.dto.TechnologyDTO;
import com.example.ProyectoOptativa.model.entity.Technology;

public class TechnologyMapper {

    public static TechnologyDTO toDTO(Technology t) {

        TechnologyDTO dto = new TechnologyDTO();

        dto.setId(t.getId());
        dto.setName(t.getName());

        return dto;
    }
}
