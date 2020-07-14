package by.resliv.task.bot.command.impl;

import by.resliv.task.bot.command.Command;
import by.resliv.task.exception.EntityIsNotExistException;
import by.resliv.task.service.CityService;

public class FindCityByNameCommand implements Command {
    private final String cityName;
    private final CityService cityService;

    public FindCityByNameCommand(String cityName,
                                 CityService cityService) {
        this.cityName = cityName;
        this.cityService = cityService;
    }

    @Override
    public String execute() {
        try {
            return cityService.getByName(cityName).getDescription();
        } catch (EntityIsNotExistException ex) {
            return ex.getMessage();
        }
    }
}
