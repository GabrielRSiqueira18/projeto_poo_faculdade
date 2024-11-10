package project.poo.estacionamento.infra.services;

import project.poo.estacionamento.infra.database.models.PassageModel;
import project.poo.estacionamento.infra.database.repositories.CarRepository;
import project.poo.estacionamento.infra.database.repositories.PassageRepository;

import java.util.List;

public class PassageServices {
  private final PassageRepository passageRepository;
  private final CarRepository     carRepository;

  public PassageServices(
    PassageRepository passageRepository,
    CarRepository carRepository
  ) {
    this.passageRepository = passageRepository;
    this.carRepository = carRepository;
  }

  public void create(PassageModel passageModel) {
    if (carRepository.findById(passageModel.getCarId()) == null)
      throw new RuntimeException("Car not found");

    if (!passageRepository.create(passageModel))
      throw new RuntimeException("Error to create passage.");

  }

  public List<PassageModel> findMany() {
    return passageRepository.findMany();
  }

  public PassageModel findById(Integer id) {
    PassageModel target = passageRepository.findById(id);
    if (target == null) {
      throw new RuntimeException("Error to find passage.");
    }

    return target;
  }

  public void deleteById(Integer id) {
    if (!passageRepository.deleteById(id)) {
      throw new RuntimeException("Error to delete passage.");
    }
  }
}
