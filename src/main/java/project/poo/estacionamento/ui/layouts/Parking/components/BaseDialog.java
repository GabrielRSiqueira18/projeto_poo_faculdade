package project.poo.estacionamento.ui.layouts.Parking.components;

import javax.swing.*;

public class BaseDialog extends JDialog {
  BaseDialog() {
    setSize(400, 300);
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
  }
}
