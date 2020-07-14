package by.resliv.task.bot.command.impl;

import by.resliv.task.bot.command.Command;

public class StartCommand implements Command {
    @Override
    public String execute() {
        return "Добро пожаловать. Кнопки управления представлены ниже.";
    }
}
