package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI extends JFrame implements IUI {

  private ArrayList<UILabel> messs=new ArrayList<>();
  protected UILabel label = new UILabel("init");
  private JTextField usrInput= new JTextField(20);
  protected JButton sendButt = new JButton("send");
  protected JPanel userBar = new JPanel();
  protected JPanel messBar = new JPanel();

  private boolean APIgiven=false;
  private TelegramLongPollingBot botAPI;

  UI(int w, int h) {
    setSize(w, h);
// Set the layout manager
    setLayout(new BorderLayout());

    // Create the top panel (fixed at the top)
    userBar.setBackground(Color.decode("#141426")); // Color: #141426
    userBar.setPreferredSize(new Dimension(getWidth(), 40)); // Height of 60 pixels

    // Create the bottom panel
    messBar.setBackground(Color.decode("#2B2E48")); // Just for visibility

    sendButt.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String text=usrInput.getText();
        if (text.equals(""))
          return;
        SendMessage mess=new SendMessage();
        mess.setChatId("5587279215");//update.getMessage().getChatId().toString());
        mess.setText(text);

        try {
          botAPI.execute(mess);
        } catch (TelegramApiException ev) {
          System.out.println(ev.getMessage());
          ev.printStackTrace();
        }
      }
    });

    userBar.add(usrInput);
    userBar.add(sendButt);

    // Add panels to the frame using BorderLayout
    add(userBar, BorderLayout.NORTH); // Add the top panel to the north (fixed position)
    add(messBar, BorderLayout.CENTER); // Add the bottom panel to the center

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  @Override
  public String getInput(){
    return usrInput.getText();
  }

  @Override
  public void setText(String str){
    label.setText(str);
  }

  @Override
  public void appendText(String str){
    UILabel temp = new UILabel(str);
    UILabel  last = (messs.size() > 0)?messs.get(messs.size()-1):null;
    int ly = null!=last?last.getY():-1;
    ly += (1&(ly>>31))|(20&~(ly>>31));
    System.out.println(ly);
    temp.setBounds(20, ly, getWidth(), 25 );
    messs.add(temp);
    messBar.add(messs.get(messs.size()-1));
    repaint();
  }

  @Override
  public void giveAPI(TelegramLongPollingBot API){
    botAPI=API;
    APIgiven=true;
  }

  //@Override
  //public void setSendFunc(Vfunc fun){sendButt.addActionListener(new ActionListener(){
  //  @Override
  //  public void actionPerformed(ActionEvent actionEvent) {

  //  }
  //});
}
