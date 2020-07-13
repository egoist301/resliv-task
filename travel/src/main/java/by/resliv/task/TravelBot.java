package by.resliv.task;

import by.resliv.task.service.CityService;
import by.resliv.task.service.dto.CityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
public class TravelBot extends TelegramLongPollingBot {
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
        CityDto cityDto = cityService.getByName(inMessage);
        sendMsg(chatId, cityDto.toString());
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private void sendMsg(final String chatId, final String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
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
}
