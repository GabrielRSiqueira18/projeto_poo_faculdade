package project.poo.estacionamento.infra.services;

import project.poo.estacionamento.infra.database.models.CarParkingModel;
import project.poo.estacionamento.infra.database.repositories.CarParkingRepository;
import project.poo.estacionamento.infra.database.repositories.CarRepository;
import project.poo.estacionamento.infra.database.repositories.dto.CreateCarParkingDto;

import java.util.List;

public class CarParkingServices {
  private final CarParkingRepository carParkingRepository;
  private final CarRepository carRepository;

  public CarParkingServices(CarParkingRepository carParkingRepository, CarRepository carRepository) {
    this.carParkingRepository = carParkingRepository;
    this.carRepository = carRepository;
  }

  public void create(CreateCarParkingDto carParking) {
    if (carRepository.findById(carParking.idCar()) == null) throw new RuntimeException("Car not found");

    if (!carParkingRepository.create(carParking)) {
      throw new RuntimeException("Error to create car.");
    }
  }

  public List<CarParkingModel> findMany() {
    return carParkingRepository.findMany();
  }

  public CarParkingModel findById(Integer id) {
    CarParkingModel target = carParkingRepository.findById(id);
    if (target == null) {
      throw new RuntimeException("Error to find car parking.");
    }

    return target;
  }

  public void deleteById(Integer id) {
    if (!carParkingRepository.deleteById(id)) {
      throw new RuntimeException("Error to delete passage.");
    }
  }
}
