package project.poo.estacionamento.infra.services;

import project.poo.estacionamento.infra.database.models.ParkingModel;
import project.poo.estacionamento.infra.database.repositories.ParkingRepository;

public class ParkingsServices {
  private final ParkingRepository parkingRepository;

  public ParkingsServices(ParkingRepository parkingRepository) {
    this.parkingRepository = parkingRepository;
  }

  public ParkingModel[][] findMany() {
    return parkingRepository.findMany();
  }
}
