package by.resliv.task.service;

import by.resliv.task.repository.CityInfoRepository;
import by.resliv.task.repository.entity.CityInfo;
import by.resliv.task.service.dto.CityInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityInfoService {
    private CityInfoRepository cityInfoRepository;

    @Autowired
    public CityInfoService(CityInfoRepository cityInfoRepository) {
        this.cityInfoRepository = cityInfoRepository;
    }

    public CityInfoDto create(CityInfoDto cityInfoDto) {
        return cityInfoRepository.save(cityInfoDto);
    }

    public CityInfoDto update(CityInfoDto cityInfoDto, Long id) {
        cityInfoDto.setId(id);
        CityInfo cityInfo = cityInfoDto;
        return cityInfoRepository.save(cityInfo);
    }

    public void delete(Long id) {
        if (cityInfoRepository.existsById(id)) {
            cityInfoRepository.delete(cityInfoRepository.findById(id).get());
        }
        else {
            throw new RuntimeException();
        }
    }
}
