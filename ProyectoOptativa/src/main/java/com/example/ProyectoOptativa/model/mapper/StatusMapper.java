package com.example.ProyectoOptativa.model.mapper;

import com.example.ProyectoOptativa.model.dto.StatusDTO;
import com.example.ProyectoOptativa.model.entity.Status;

public class StatusMapper {

    public static StatusDTO toDTO(Status s) {

        if (s == null) return null;

        StatusDTO dto = new StatusDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());

        return dto;
    }
}
