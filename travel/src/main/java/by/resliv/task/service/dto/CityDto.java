package by.resliv.task.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class CityDto {
    @JsonProperty("id")
    private Long id;
    @NotBlank(message = "Name can't be null or empty")
    @JsonProperty("name")
    private String name;
    @Valid
    @JsonProperty("city_info")
    private List<CityInfoDto> cityInfoDtos;

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

    public List<CityInfoDto> getCityInfoDtos() {
        return cityInfoDtos;
    }

    public void setCityInfoDtos(List<CityInfoDto> cityInfoDtos) {
        this.cityInfoDtos = cityInfoDtos;
    }
}
