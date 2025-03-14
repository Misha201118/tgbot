package org.example;

import javax.swing.*;
import java.awt.*;

public class UILabel extends JLabel {

  //@Override
  UILabel(String text){
    super(text);
    setForeground(Color.WHITE);
  }

  UILabel(){
    super();
    setForeground(Color.WHITE);
  }
}
