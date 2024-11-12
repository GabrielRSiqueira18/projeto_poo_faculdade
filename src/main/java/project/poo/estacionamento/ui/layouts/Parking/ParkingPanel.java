package project.poo.estacionamento.ui.layouts.Parking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingPanel extends JPanel {
  public static int parkingZoneNumber = 1;

  private ChoiceParkingPanel[] choicesParkingPanel = new ChoiceParkingPanel[5];
  private int currentIndex = 0;
  private JPanel contentPanel;

  public ParkingPanel() {
    setLayout(new BorderLayout());

    for (int i = 0; i < choicesParkingPanel.length; i++) {
      ChoiceParkingPanel panel = new ChoiceParkingPanel();
      panel.setPreferredSize(new Dimension((768 - 250), 350));
      choicesParkingPanel[i] = panel;
    }

    contentPanel = new JPanel(new BorderLayout());
    contentPanel.add(choicesParkingPanel[currentIndex], BorderLayout.CENTER);
    add(contentPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    JButton previousButton = new JButton("Anterior");
    JButton nextButton = new JButton("Próximo");

    buttonPanel.add(previousButton);
    buttonPanel.add(nextButton);
    add(buttonPanel, BorderLayout.SOUTH);

    nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showNextPanel();
      }
    });

    previousButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showPreviousPanel();
      }
    });
  }

  private void showNextPanel() {
    contentPanel.remove(choicesParkingPanel[currentIndex]);

    currentIndex = (currentIndex + 1) % choicesParkingPanel.length;

    contentPanel.add(choicesParkingPanel[currentIndex], BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
  }

  private void showPreviousPanel() {
    contentPanel.remove(choicesParkingPanel[currentIndex]);

    currentIndex = (currentIndex - 1 + choicesParkingPanel.length) % choicesParkingPanel.length;

    contentPanel.add(choicesParkingPanel[currentIndex], BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
  }
}
