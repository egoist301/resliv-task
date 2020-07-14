package by.resliv.task.service.converter;

import by.resliv.task.repository.entity.City;
import by.resliv.task.service.dto.CityDto;

public final class CityDtoConverter {
    private CityDtoConverter() {
    }

    public static CityDto convertToDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        cityDto.setDescription(city.getDescription());
        return cityDto;
    }

    public static City convertToEntity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        city.setDescription(cityDto.getDescription());
        return city;
    }
}
