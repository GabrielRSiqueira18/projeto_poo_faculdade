package project.poo.estacionamento.infra.services;

import project.poo.estacionamento.infra.database.models.CarModel;
import project.poo.estacionamento.infra.database.repositories.CarRepository;

import java.util.List;

public class CarServices {
  private final CarRepository carRepository;

  public CarServices(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public void create(CarModel carModel) {
    if (!carRepository.create(carModel)) {
      throw new RuntimeException("Error to create car.");
    }
  }

  public List<CarModel> findMany() {
    return carRepository.findMany();
  }

  public CarModel findById(Integer id) {
    CarModel target = carRepository.findById(id);
    if (target == null) {
      throw new RuntimeException("Error to find car.");
    }

    return target;
  }
}
