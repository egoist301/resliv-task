package by.resliv.task.controller;

import by.resliv.task.service.CityInfoService;
import by.resliv.task.service.dto.CityInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CityInfoController {
    private CityInfoService cityInfoService;

    @Autowired
    public CityInfoController(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityInfoDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityInfoDto> add(@RequestBody @Valid CityInfoDto cityInfoDto) {
        return new ResponseEntity<>(cityInfoService.create(cityInfoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityInfoDto> edit(@RequestBody @Valid CityInfoDto cityInfoDto, @PathVariable Long id) {
        return new ResponseEntity<>(cityInfoService.update(cityInfoDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityInfoService.delete(id);
    }
}
