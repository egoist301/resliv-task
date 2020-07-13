package by.resliv.task.service.converter;

import by.resliv.task.repository.entity.CityInfo;
import by.resliv.task.service.dto.CityInfoDto;

public final class CityInfoDtoConverter {
    private CityInfoDtoConverter() {

    }

    public static CityInfo convertToEntity(CityInfoDto cityInfoDto) {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setId(cityInfoDto.getId());
        cityInfo.setInfo(cityInfoDto.getInfo());
        return cityInfo;
    }

    public static CityInfoDto convertToDto(CityInfo cityInfo) {
        CityInfoDto cityInfoDto = new CityInfoDto();
        cityInfoDto.setId(cityInfo.getId());
        cityInfoDto.setInfo(cityInfo.getInfo());
        return cityInfoDto;
    }
}
