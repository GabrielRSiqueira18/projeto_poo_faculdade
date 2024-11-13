package project.poo.estacionamento.core.factories;

import project.poo.estacionamento.core.Repositories;
import project.poo.estacionamento.infra.database.repositories.*;

public class CreateRepositoriesFactory {
  public static Repositories make() {
    return Repositories.create(
      new UserRepository(),
      new CarRepository(),
      new PassageRepository(),
      new CarParkingRepository(),
      new ParkingRepository()
    );
  }
}
