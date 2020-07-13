package by.resliv.task.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CityDto {
    @JsonProperty("id")
    private Long id;
    @NotBlank(message = "Name can't be null or empty")
    @JsonProperty("name")
    private String name;
    @NotBlank(message = "Description can't be null or empty")
    @JsonProperty("description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}
