package project.poo.estacionamento.infra.database.models;

import java.util.Objects;

public class CarModel {
  private final Integer id;
  private       String  model;
  private       String  licensePlate;

  public CarModel(Integer id, String licensePlate, String model) {
    this.id = id;
    this.licensePlate = licensePlate;
    this.model = model;
  }

  public Integer getId() {
    return id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  @Override public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CarModel carModel = (CarModel) o;
    return Objects.equals(id, carModel.id) &&
           Objects.equals(model, carModel.model);
  }

  @Override public int hashCode() {
    return Objects.hashCode(id);
  }
}
