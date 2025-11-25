package com.example.ProyectoOptativa.model.dto;

import java.time.LocalDate;
import java.util.List;

public class ProjectDTO {

    private Integer id;
    private String name;
    private String description;
    private String demoUrl;
    private String picture;
    private LocalDate startDate;
    private LocalDate endDate;

    private StatusDTO status;
    private List<TechnologyDTO> technologies;
    private List<DeveloperDTO> developers;

    // ===== Getters y setters =====

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public StatusDTO getStatus() { return status; }
    public void setStatus(StatusDTO status) { this.status = status; }

    public List<TechnologyDTO> getTechnologies() { return technologies; }
    public void setTechnologies(List<TechnologyDTO> technologies) { this.technologies = technologies; }

    public List<DeveloperDTO> getDevelopers() { return developers; }
    public void setDevelopers(List<DeveloperDTO> developers) { this.developers = developers; }
}
