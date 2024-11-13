package project.poo.estacionamento.ui.layouts.Parking;

import project.poo.estacionamento.infra.database.models.ParkingModel;
import project.poo.estacionamento.ui.layouts.Parking.components.CreateCarParkingDialog;
import project.poo.estacionamento.ui.layouts.Parking.components.ParkingButton;
import project.poo.estacionamento.ui.layouts.Parking.components.RemoveCarParkingDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static project.poo.estacionamento.Main.parkingsServices;

public class ChoiceParkingPanel extends JPanel {
  public ChoiceParkingPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    setBackground(Color.gray);

    ParkingModel[][] parkings = parkingsServices.findMany();

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
        button.props.cpf = parkings[i][j].getCpf();
        button.props.dateEnded = parkings[i][j].getDateEnded();
        button.props.dateStarted = parkings[i][j].getDateStarted();
        button.props.occupied = parkings[i][j].isOccupied();
        button.props.priceValue = parkings[i][j].getPriceValue();
        button.props.personName = parkings[i][j].getPersonName();
        button.props.licensePlate = parkings[i][j].getLicensePlate();

        if (button.props.occupied) {
          button.setText("");
          button.setHorizontalAlignment(SwingConstants.CENTER);
          button.setVerticalAlignment(SwingConstants.CENTER);

          button.setHorizontalTextPosition(SwingConstants.CENTER);
          button.setVerticalTextPosition(SwingConstants.CENTER);
          button.setBackground(new Color(250, 125, 125));

          button.setIcon(new ImageIcon(getClass().getResource("/icons/hatchback.png")));
        } else {
          button.setBackground(Color.gray);
          button.setText("Vaga: " + button.props.id);
          button.setIcon(null);
        }

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

        add(button, gbc);
      }
    }
  }

  public static class ParkingButtonListener implements ActionListener {
    private final ParkingButton button;

    public ParkingButtonListener(ParkingButton button) {
      this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if (!button.props.occupied) {
        new CreateCarParkingDialog(button).setVisible(true);

      } else {
        new RemoveCarParkingDialog(button).setVisible(true);
        button.setBackground(Color.gray);
        button.setText("Vaga: " + button.props.id);
        button.setIcon(null);
        button.props.occupied = false;

      }
    }
  }
}