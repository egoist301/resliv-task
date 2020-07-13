package by.resliv.task.controller;

import by.resliv.task.service.CityInfoService;
import by.resliv.task.service.dto.CityInfoDto;
import by.resliv.task.util.NumberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/info")
public class CityInfoController {
    private CityInfoService cityInfoService;

    @Autowired
    public CityInfoController(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityInfoDto> get(@PathVariable Long id) {
        NumberValidator.validateId(id);
        return new ResponseEntity<>(cityInfoService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityInfoDto> add(@RequestBody @Valid CityInfoDto cityInfoDto) {
        CityInfoDto cityInfoDtoResponse = cityInfoService.create(cityInfoDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                        .buildAndExpand(cityInfoDtoResponse.getId()).toUri());
        return new ResponseEntity<>(cityInfoDtoResponse, httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CityInfoDto> edit(@RequestBody @Valid CityInfoDto cityInfoDto, @PathVariable Long id) {
        NumberValidator.validateId(id);
        return new ResponseEntity<>(cityInfoService.update(cityInfoDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        NumberValidator.validateId(id);
        cityInfoService.delete(id);
    }
}
