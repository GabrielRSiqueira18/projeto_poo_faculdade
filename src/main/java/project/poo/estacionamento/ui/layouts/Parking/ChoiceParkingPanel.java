package project.poo.estacionamento.ui.layouts.Parking;

import project.poo.estacionamento.ui.layouts.Parking.components.CreateCarParkingDialog;
import project.poo.estacionamento.ui.layouts.Parking.components.ParkingButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceParkingPanel extends JPanel {
  private JButton[][] parkingButtons;

  public ChoiceParkingPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    setBackground(Color.gray);

    parkingButtons = new JButton[5][5];



    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (i % 2 == 1) {
          JPanel jPanel = new JPanel();
          jPanel.setBackground(Color.GRAY);

          gbc.gridx = j;
          gbc.gridy = i;
          add(jPanel, gbc);
          continue;
        }

        int top = 1;
        int bottom = 1;
        int left = 1;
        int right = 1;

        if (i % 2 == 0) {
          bottom = 0;
        } else {
          top = 0;
        }

        ParkingButton button = new ParkingButton(top, left, bottom, right);
        button.addActionListener(new ParkingButtonListener(button));


        gbc.gridx = j;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;


        int leftInset = 5;
        int rightInset = 5;
        gbc.insets = new Insets(0, leftInset, 0, rightInset);

        parkingButtons[i][j] = button;
        add(button, gbc);
      }
    }
  }

  public static class ParkingButtonListener implements ActionListener {
    private final JButton button;
    private boolean isOccupied = false;

    public ParkingButtonListener(JButton button) {
      this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      isOccupied = !isOccupied;
      if (isOccupied) {
        new CreateCarParkingDialog().setVisible(true)  ;
        button.setBackground(Color.red);
        button.setText("Ocupado");
      } else {
        button.setBackground(Color.green);
        button.setText("Disponível");
      }
    }
  }
}