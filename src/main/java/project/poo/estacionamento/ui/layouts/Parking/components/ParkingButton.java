package project.poo.estacionamento.ui.layouts.Parking.components;

import project.poo.estacionamento.ui.layouts.Parking.ChoiceParkingPanel;

import javax.swing.*;
import java.awt.*;

import static project.poo.estacionamento.ui.layouts.Parking.ParkingPanel.parkingZoneNumber;

public class ParkingButton extends JButton {
  public ParkingButton(int top, int left, int bottom, int right) {
    super("Código Vaga: " + parkingZoneNumber);
    parkingZoneNumber += 1;
    setBackground(Color.gray);
    setOpaque(true);
    setFont(new Font("Arial", Font.PLAIN, 10));
    setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    setFocusPainted(false);
    setPreferredSize(new Dimension(getWidth(), 50));
  }
}
