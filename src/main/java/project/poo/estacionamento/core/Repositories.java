package project.poo.estacionamento.core;

import project.poo.estacionamento.infra.database.repositories.*;

public class Repositories {
  private final CarRepository carRepository;
  private final CarParkingRepository carParkingRepository;
  private final UserRepository userRepository;
  private final PassageRepository passageRepository;
  private final ParkingRepository parkingRepository;

  private Repositories(
    UserRepository userRepository,
    CarRepository carRepository,
    PassageRepository passageRepository,
    CarParkingRepository carParkingRepository,
    ParkingRepository parkingRepository
  ) {
    this.userRepository = userRepository;
    this.carRepository = carRepository;
    this.passageRepository = passageRepository;
    this.carParkingRepository = carParkingRepository;
    this.parkingRepository = parkingRepository;
  }

  public static Repositories create(
    UserRepository userRepository,
    CarRepository carRepository,
    PassageRepository passageRepository,
    CarParkingRepository carParkingRepository,
    ParkingRepository parkingRepository
  ) {
    return new Repositories(
      userRepository,
      carRepository,
      passageRepository,
      carParkingRepository,
      parkingRepository
    );
  }

  public CarRepository getCarRepository() {
    return carRepository;
  }

  public CarParkingRepository getCarParkingRepository() { return carParkingRepository; }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public PassageRepository getPassageRepository() {
    return passageRepository;
  }

  public ParkingRepository getParkingRepository() { return parkingRepository; }
}
