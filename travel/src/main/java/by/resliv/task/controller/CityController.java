package by.resliv.task.controller;

import by.resliv.task.repository.entity.City;
import by.resliv.task.service.CityService;
import by.resliv.task.service.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("city")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityDto> add(@RequestBody @Valid CityDto cityDto) {
        return new ResponseEntity<>(cityService.create(cityDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> edit(@RequestBody @Valid CityDto cityDto, @PathVariable Long id) {
        return new ResponseEntity<>(cityService.update(cityDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
