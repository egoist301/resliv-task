package by.resliv.task.service;

import by.resliv.task.service.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getAll();
    CityDto get(Long id);
    CityDto getByName(String name);
    CityDto create(CityDto cityDto);
    CityDto update(CityDto cityDto, Long id);
    void delete(Long id);
}
