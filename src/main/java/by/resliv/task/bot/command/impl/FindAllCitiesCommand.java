package by.resliv.task.bot.command.impl;

import by.resliv.task.bot.command.Command;
import by.resliv.task.service.CityService;
import by.resliv.task.service.dto.CityDto;

import java.util.stream.Collectors;

public class FindAllCitiesCommand implements Command {
    private final CityService cityService;

    public FindAllCitiesCommand(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String execute() {
        return cityService.getAll().stream()
                .map(CityDto::getName)
                .collect(Collectors.joining(",  "));
    }
}
