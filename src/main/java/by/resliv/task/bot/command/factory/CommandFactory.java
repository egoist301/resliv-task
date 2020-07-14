package by.resliv.task.bot.command.factory;

import by.resliv.task.bot.command.Command;

@FunctionalInterface
public interface CommandFactory {
    Command findCommand(String action);
}
