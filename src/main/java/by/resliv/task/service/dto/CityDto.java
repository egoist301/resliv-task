package by.resliv.task.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CityDto {
    @JsonProperty("id")
    private Long id;
    @NotBlank(message = "Name can't be null or empty")
    @Size(min = 3, max = 60, message = "Name can be 3 to 60 characters long")
    @JsonProperty("name")
    private String name;
    @NotBlank(message = "Description can't be null or empty")
    @Size(min = 3, max = 255, message = "Description can be 3 to 255 characters long")
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
