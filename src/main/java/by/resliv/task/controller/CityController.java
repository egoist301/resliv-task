package by.resliv.task.controller;

import by.resliv.task.service.CityService;
import by.resliv.task.service.dto.CityDto;
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
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> get(@PathVariable Long id) {
        NumberValidator.validateId(id);
        return new ResponseEntity<>(cityService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CityDto> getByName(@PathVariable String name) {
        return new ResponseEntity<>(cityService.getByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityDto> add(@RequestBody @Valid CityDto cityDto) {
        CityDto cityDtoResponse = cityService.create(cityDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                        .buildAndExpand(cityDtoResponse.getId()).toUri());
        return new ResponseEntity<>(cityDtoResponse, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> edit(@RequestBody @Valid CityDto cityDto, @PathVariable Long id) {
        NumberValidator.validateId(id);
        return new ResponseEntity<>(cityService.update(cityDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        NumberValidator.validateId(id);
        cityService.delete(id);
    }
}
