package project.poo.estacionamento.ui.components;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static project.poo.estacionamento.Main.carParkingServices;
import static project.poo.estacionamento.Main.carServices;

public class Nav extends JPanel {
  private JLabel welcomeLabel = new JLabel("Bem vindo!");
  private JLabel dateLabel = new JLabel("");
  private JLabel carsRegisteredLabel = new JLabel("Carros cadastrados:");
  private JLabel carsParkedLabel = new JLabel("Carros estacionados:");

  private JButton registerCarButton = new JButton("Cadastrar Carro");
  private JButton viewCarButton = new JButton("Ver Carro");
  private JButton updateCarButton = new JButton("Atualizar Carro");
  private JButton removeCarButton = new JButton("Remover Carro");
  private JButton fineCarButton = new JButton("Multar Carro");
  private JButton viewFinedCarsButton = new JButton("Ver Carros Multados");

  private void updateDateNowText() {
    new Thread(() -> {
      while (true) {
        try {
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          String currentDate = sdf.format(new Date());
          SwingUtilities.invokeLater(() -> dateLabel.setText("Data e hora atual: " + currentDate));
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private void updateCarsText() {
    int carQuantity = carServices.findMany().size();
    carsRegisteredLabel.setText("Carros cadastrados: " + carQuantity);
  }

  private void updateCarsParkedText() {
    int carsParkedQuantity = carParkingServices.findMany().size();
    carsParkedLabel.setText("Carros estacionados: " + carsParkedQuantity);
  }

  public Nav() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(Box.createVerticalStrut(20));

    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    carsRegisteredLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    carsParkedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    setBackground(Color.blue);

    add(welcomeLabel);
    add(Box.createVerticalStrut(10));
    add(dateLabel);
    add(Box.createVerticalStrut(10));
    add(carsRegisteredLabel);
    add(Box.createVerticalStrut(10));
    add(carsParkedLabel);
    add(Box.createVerticalStrut(20));

    updateDateNowText();
    updateCarsText();
    updateCarsParkedText();
  }

}
