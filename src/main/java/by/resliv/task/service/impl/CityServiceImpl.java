package by.resliv.task.service.impl;

import by.resliv.task.exception.*;
import by.resliv.task.repository.CityRepository;
import by.resliv.task.repository.entity.City;
import by.resliv.task.service.CityService;
import by.resliv.task.service.converter.CityDtoConverter;
import by.resliv.task.service.dto.CityDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private static final String CITY_IS_NOT_EXIST = "city is not exist";
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
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

    public List<CityDto> getAll() {
        return cityRepository.findAll().stream().map(CityDtoConverter::convertToDto).collect(Collectors.toList());
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
        if (cityRepository.existsByName(cityDto.getName())) {
            throw new EntityIsAlreadyExistException("city with name " + cityDto.getName() + " is already exist.");
        } else {
            return CityDtoConverter.convertToDto(cityRepository.save(CityDtoConverter.convertToEntity(cityDto)));
        }
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
