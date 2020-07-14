package by.resliv.task.bot;

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

@Component
public class TravelBot extends TelegramLongPollingBot {
    private static final String AVAILABLE_CITIES = "Доступные города";
    private static final String START = "/start";
    @Value("${telegram.token}")
    private String botToken;
    @Value("${telegram.username}")
    private String botUsername;
    private CityService cityService;
    private TelegramBotsApi telegramBotsApi;

    public TravelBot(CityService cityService, TelegramBotsApi telegramBotsApi) {
        this.cityService = cityService;
        this.telegramBotsApi = telegramBotsApi;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String inMessage;
        String chatId;
        if (update.hasEditedMessage()) {
            inMessage = update.getEditedMessage().getText();
            chatId = update.getEditedMessage().getChatId().toString();
        } else {
            inMessage = update.getMessage().getText();
            chatId = update.getMessage().getChatId().toString();
        }
        chooseCommand(chatId, inMessage);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private void chooseCommand(String chatId, String inMessage) {
        switch (inMessage) {
            case START:
                sendMsg(chatId, "Добро пожаловать. Кнопки управления представлены ниже.");
                break;
            case AVAILABLE_CITIES:
                cityService.getAll().stream().map(CityDto::getName).forEachOrdered(s -> sendMsg(chatId, s));
                break;
            default:
                try {
                    sendMsg(chatId, cityService.getByName(inMessage).toString());
                } catch (EntityIsNotExistException e) {
                    sendMsg(chatId, "Город не найден");
                }
                break;
        }
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
