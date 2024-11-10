package project.poo.estacionamento.infra.database.models;

import java.time.ZonedDateTime;
import java.util.Objects;

public class CarParkingModel {
  private final Integer       id;
  private final Integer       idCar;
  private final float         priceValue;
  private final float         interest;
  private final ZonedDateTime startEntryTime;
  private final ZonedDateTime endEntryTime;

  public CarParkingModel(
    Integer id,
    Integer idCar,
    float priceValue,
    float interest,
    ZonedDateTime startEntryTime,
    ZonedDateTime endEntryTime
  ) {
    this.id = id;
    this.idCar = idCar;
    this.priceValue = priceValue;
    this.interest = interest;
    this.startEntryTime = startEntryTime;
    this.endEntryTime = endEntryTime;
  }

  public Integer getId() {
    return id;
  }

  public Integer getIdCar() {
    return idCar;
  }

  public float getPriceValue() {
    return priceValue;
  }

  public float getInterest() {
    return interest;
  }

  public ZonedDateTime getStartEntryTime() {
    return startEntryTime;
  }

  public ZonedDateTime getEndEntryTime() {
    return endEntryTime;
  }

  @Override public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CarParkingModel that = (CarParkingModel) o;
    return Objects.equals(id, that.id);
  }

  @Override public int hashCode() {
    return Objects.hashCode(id);
  }
}
