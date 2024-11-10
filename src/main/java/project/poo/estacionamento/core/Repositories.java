package project.poo.estacionamento.core;

import project.poo.estacionamento.infra.database.repositories.CarParkingRepository;
import project.poo.estacionamento.infra.database.repositories.CarRepository;
import project.poo.estacionamento.infra.database.repositories.PassageRepository;
import project.poo.estacionamento.infra.database.repositories.UserRepository;

public class Repositories {
  private final CarRepository carRepository;
  private final CarParkingRepository carParkingRepository;
  private final UserRepository userRepository;
  private final PassageRepository passageRepository;

  private Repositories(
    UserRepository userRepository,
    CarRepository carRepository,
    PassageRepository passageRepository,
    CarParkingRepository carParkingRepository
  ) {
    this.userRepository = userRepository;
    this.carRepository = carRepository;
    this.passageRepository = passageRepository;
    this.carParkingRepository = carParkingRepository;
  }

  public static Repositories create(
    UserRepository userRepository,
    CarRepository carRepository,
    PassageRepository passageRepository,
    CarParkingRepository carParkingRepository
  ) {
    return new Repositories(
      userRepository,
      carRepository,
      passageRepository,
      carParkingRepository
    );
  }

  public CarRepository getCarRepository() {
    return carRepository;
  }

  public CarParkingRepository getCarParkingRepository() {
    return carParkingRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public PassageRepository getPassageRepository() {
    return passageRepository;
  }
}
