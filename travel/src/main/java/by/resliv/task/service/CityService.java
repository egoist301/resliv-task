package by.resliv.task.service;

import by.resliv.task.repository.CityRepository;
import by.resliv.task.repository.entity.City;
import by.resliv.task.service.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDto create(CityDto cityDto) {
        return cityRepository.save(cityDto);
    }

    public CityDto update(CityDto cityDto, Long id) {
        cityDto.setId(id);
        City city = cityDto;
        return cityRepository.save(city);
    }

    public void delete(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.delete(cityRepository.findById(id).get());
        }
        else {
            throw new RuntimeException();
        }
    }
}
