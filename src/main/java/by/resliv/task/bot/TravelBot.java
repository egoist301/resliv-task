package by.resliv.task.bot;

import by.resliv.task.bot.command.Command;
import by.resliv.task.bot.command.factory.CommandFactory;
import by.resliv.task.exception.EntityIsNotExistException;
import by.resliv.task.service.CityService;
import by.resliv.task.service.dto.CityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.Collections;

import static by.resliv.task.bot.BotConstants.AVAILABLE_CITIES;

@Component
public class TravelBot extends TelegramLongPollingBot {
    @Value("${telegram.token}")
    private String botToken;
    @Value("${telegram.username}")
    private String botUsername;
    private CommandFactory commandFactory;
    private TelegramBotsApi telegramBotsApi;

    public TravelBot(CommandFactory commandFactory, TelegramBotsApi telegramBotsApi) {
        this.commandFactory = commandFactory;
        this.telegramBotsApi = telegramBotsApi;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String inMessage = getInMessage(update);
        String chatId = getChatId(update);
        Command command = commandFactory.findCommand(inMessage);
        String text = command.execute();
        sendMsg(chatId, text);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private String getInMessage(Update update) {
        return update.hasEditedMessage() ?
               update.getEditedMessage().getText() :
               update.getMessage().getText();
    }

    private String getChatId(Update update) {
        return update.hasEditedMessage() ?
               update.getEditedMessage().getChatId().toString() :
               update.getMessage().getChatId().toString();
    }

    private void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void registryBot() {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void setButtons(final SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(AVAILABLE_CITIES));
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardFirstRow));
    }
}
