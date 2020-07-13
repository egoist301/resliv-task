package by.resliv.task.service.converter;

import by.resliv.task.repository.entity.City;
import by.resliv.task.service.dto.CityDto;

import java.util.Objects;
import java.util.stream.Collectors;


public final class CityDtoConverter {
    private CityDtoConverter() {
    }

    public static CityDto convertToDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        if (Objects.nonNull(city.getCityInfos())) {
            cityDto.setCityInfoDtos(city.getCityInfos().stream().map(CityInfoDtoConverter::convertToDto).collect(
                    Collectors.toList()));
        }
        return cityDto;
    }

    public static City convertToEntity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        if (Objects.nonNull(cityDto.getCityInfoDtos())) {
            city.setCityInfos(cityDto.getCityInfoDtos().stream().map(CityInfoDtoConverter::convertToEntity).collect(
                    Collectors.toSet()));
        }
        return city;
    }
}
