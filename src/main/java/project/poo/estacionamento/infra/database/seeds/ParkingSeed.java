package project.poo.estacionamento.infra.database.seeds;

import project.poo.estacionamento.infra.database.models.ParkingModel;
import project.poo.estacionamento.infra.database.repositories.ParkingRepository;
import project.poo.estacionamento.ui.layouts.Parking.ChoiceParkingPanel;
import project.poo.estacionamento.ui.layouts.Parking.components.ParkingButton;

import javax.swing.*;
import java.awt.*;

public class ParkingSeed {
  public static void seed() {
    ParkingRepository parkingRepository = new ParkingRepository();

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        parkingRepository.create(new ParkingModel(
          null,
          null,
          null,
          null,
          null,
          null,
          false,
          null,
          Double.valueOf(0),
          i,
          j
        ));
      }
    }
  }
}
