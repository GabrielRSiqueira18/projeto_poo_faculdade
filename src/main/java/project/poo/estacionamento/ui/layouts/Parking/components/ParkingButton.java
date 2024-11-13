package project.poo.estacionamento.ui.layouts.Parking.components;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static project.poo.estacionamento.ui.layouts.Parking.ParkingPanel.parkingZoneNumber;

public class ParkingButton extends JButton {
  public class Props {
    public Integer          id;
    public String        personName;
    public String        licensePlate;
    public LocalDateTime dateStarted;
    public LocalDateTime dateEnded;
    public boolean       occupied;
    public String        passwordToPay;
    public double         priceValue;
  }

  public Props props = new Props();

  public ParkingButton(int top, int left, int bottom, int right) {
    super("Vaga: " + parkingZoneNumber);
    props.id = parkingZoneNumber;
    parkingZoneNumber += 1;
    setBackground(Color.gray);
    setOpaque(true);
    setFont(new Font("Arial", Font.PLAIN, 10));
    setBorder(BorderFactory.createMatteBorder(
      top,
      left,
      bottom,
      right,
      Color.black
    ));
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    setFocusPainted(false);
    setPreferredSize(new Dimension(getWidth(), 50));
  }
}
