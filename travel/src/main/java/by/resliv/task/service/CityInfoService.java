package by.resliv.task.service;

import by.resliv.task.exception.EntityIsNotExistException;
import by.resliv.task.repository.CityInfoRepository;
import by.resliv.task.repository.entity.CityInfo;
import by.resliv.task.service.converter.CityInfoDtoConverter;
import by.resliv.task.service.dto.CityInfoDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityInfoService {
    private static final String INFO_ABOUT_CITY_IS_NOT_EXIST = "Info about city is not exist";
    private CityInfoRepository cityInfoRepository;

    public CityInfoService(CityInfoRepository cityInfoRepository) {
        this.cityInfoRepository = cityInfoRepository;
    }

    public CityInfoDto get(Long id) {
        Optional<CityInfo> cityInfo = cityInfoRepository.findById(id);
        if (cityInfo.isPresent()) {
            return CityInfoDtoConverter.convertToDto(cityInfo.get());
        } else {
            throw new EntityIsNotExistException(INFO_ABOUT_CITY_IS_NOT_EXIST);
        }
    }

    public CityInfoDto create(CityInfoDto cityInfoDto) {
        return CityInfoDtoConverter
                .convertToDto(cityInfoRepository.save(CityInfoDtoConverter.convertToEntity(cityInfoDto)));
    }

    public CityInfoDto update(CityInfoDto cityInfoDto, Long id) {
        if (cityInfoRepository.existsById(id)) {
            cityInfoDto.setId(id);
            CityInfo cityInfo = CityInfoDtoConverter.convertToEntity(cityInfoDto);
            return CityInfoDtoConverter.convertToDto(cityInfoRepository.save(cityInfo));
        } else {
            throw new EntityIsNotExistException(INFO_ABOUT_CITY_IS_NOT_EXIST);
        }
    }

    public void delete(Long id) {
        if (cityInfoRepository.existsById(id)) {
            cityInfoRepository.delete(cityInfoRepository.findById(id).get());
        } else {
            throw new EntityIsNotExistException(INFO_ABOUT_CITY_IS_NOT_EXIST);
        }
    }
}
