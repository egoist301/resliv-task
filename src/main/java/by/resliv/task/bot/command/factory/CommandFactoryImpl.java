package by.resliv.task.bot.command.factory;

import by.resliv.task.bot.command.Command;
import by.resliv.task.bot.command.impl.FindAllCitiesCommand;
import by.resliv.task.bot.command.impl.FindCityByNameCommand;
import by.resliv.task.bot.command.impl.StartCommand;
import by.resliv.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static by.resliv.task.bot.BotConstants.AVAILABLE_CITIES;
import static by.resliv.task.bot.BotConstants.START;

@Component
public class CommandFactoryImpl implements CommandFactory {

    private final CityService cityService;

    @Autowired
    public CommandFactoryImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public Command findCommand(String action) {
        switch (action) {
            case START:
                return new StartCommand();
            case AVAILABLE_CITIES:
                return new FindAllCitiesCommand(cityService);
            default:
                return new FindCityByNameCommand(action, cityService);
        }
    }
}
