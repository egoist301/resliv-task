package by.resliv.task.service;

import by.resliv.task.service.dto.CityDto;

public interface CityService {
    CityDto get(Long id);
    CityDto getByName(String name);
    CityDto create(CityDto cityDto);
    CityDto update(CityDto cityDto, Long id);
    void delete(Long id);
}
