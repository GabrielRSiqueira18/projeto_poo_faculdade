package project.poo.estacionamento.ui.layouts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static project.poo.estacionamento.ui.layouts.ParkingPanel.parkingZoneNumber;

public class ChoiceParkingPanel extends JPanel {
  private JButton[][] parkingButtons;

  public ChoiceParkingPanel() {
    setLayout(new GridLayout(5, 5, 5, 5)); // Define layout 5x5 com espaçamento entre os botões
    setBackground(Color.gray);

    parkingButtons = new JButton[5][5]; // Ajusta a matriz para 5x5

    // Cria e adiciona os botões ao painel com bordas personalizadas

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        JButton button = new JButton("V" + parkingZoneNumber);
        parkingZoneNumber += 1;
        button.setBackground(Color.green); // Define cor inicial (disponível)
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.PLAIN, 10)); // Fonte menor para caber no botão

        // Define bordas mais finas
        int top = (i == 0) ? 2 : 1;
        int left = (j == 0) ? 2 : 1;
        int bottom = (i == 4) ? 2 : 1;
        int right = (j == 4) ? 2 : 1;
        button.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));

        // Adiciona uma ação ao botão para simular a ocupação/desocupação
        button.addActionListener(new ParkingButtonListener(button));

        parkingButtons[i][j] = button;
        add(button); // Adiciona o botão ao painel
      }
    }
  }

  // Classe interna para tratar a ação dos botões de estacionamento
  private static class ParkingButtonListener implements ActionListener {
    private final JButton button;
    private boolean isOccupied = false;

    public ParkingButtonListener(JButton button) {
      this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // Alterna entre ocupado e disponível ao clicar no botão
      isOccupied = !isOccupied;
      if (isOccupied) {
        button.setBackground(Color.red);
        button.setText("Ocupado");
      } else {
        button.setBackground(Color.green);
        button.setText("Disponível");
      }
    }
  }
}