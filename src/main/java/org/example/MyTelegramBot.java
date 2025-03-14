package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//import java.util.Scanner;

public class MyTelegramBot extends TelegramLongPollingBot {
  private IUI ui;
  MyTelegramBot(IUI ui){
    this.ui=ui;
    this.ui.giveAPI(this);
  }

  @Override
  public String getBotUsername() {
    return "name"; // Замініть на ім'я вашого бота
  }

  @Override
  public String getBotToken() {
    return "token"; // Замініть на токен вашого бота
  }

  @Override
  public void onUpdateReceived(Update update) {
    // Перевіряємо, чи є оновлення повідомленням від користувача
    if (update.hasMessage() && update.getMessage().hasText()) {
      Message mess = update.getMessage();
      String userMessage = update.getMessage().getText();
      User author = mess.getFrom();
      long chatId=mess.getChatId();
      String out = String.format("[%s][%d]: %s\n", author.getUserName(), chatId, userMessage);
      System.out.printf(out);
      ui.appendText(out);

      SendMessage message = new SendMessage();
      message.setChatId("5587279215");//update.getMessage().getChatId().toString());
      message.setText(userMessage);

      try {
        execute(message);
      } catch (TelegramApiException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }

    }else {
      System.out.println("Received non-text message");
    }
  }
}
