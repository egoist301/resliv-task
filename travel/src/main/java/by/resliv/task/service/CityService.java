package by.resliv.task.service;

import by.resliv.task.exception.EntityIsNotExistException;
import by.resliv.task.repository.CityRepository;
import by.resliv.task.repository.entity.City;
import by.resliv.task.service.converter.CityDtoConverter;
import by.resliv.task.service.dto.CityDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    private static final String CITY_IS_NOT_EXIST = "city is not exist";
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDto get(Long id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            return CityDtoConverter.convertToDto(city.get());
        } else {
            throw new EntityIsNotExistException(CITY_IS_NOT_EXIST);
        }
    }

    public CityDto getByName(String name) {
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()) {
            return CityDtoConverter.convertToDto(city.get());
        } else {
            throw new EntityIsNotExistException(CITY_IS_NOT_EXIST);
        }
    }

    public CityDto create(CityDto cityDto) {
        return CityDtoConverter.convertToDto(cityRepository.save(CityDtoConverter.convertToEntity(cityDto)));
    }

    public CityDto update(CityDto cityDto, Long id) {
        if (cityRepository.existsById(id)) {
            cityDto.setId(id);
            City city = CityDtoConverter.convertToEntity(cityDto);
            return CityDtoConverter.convertToDto(cityRepository.save(city));
        } else {
            throw new EntityIsNotExistException(CITY_IS_NOT_EXIST);
        }
    }

    public void delete(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.delete(cityRepository.findById(id).get());
        } else {
            throw new EntityIsNotExistException(CITY_IS_NOT_EXIST);
        }
    }
}
