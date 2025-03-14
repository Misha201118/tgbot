package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.awt.event.ActionListener;

public interface IUI {
  //void setSendFunc(Vfunc func);
  String getInput();
  void setText(String str);
  void appendText(String str);
  void giveAPI(TelegramLongPollingBot Bot);
}
