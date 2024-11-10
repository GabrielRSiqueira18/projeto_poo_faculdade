package project.poo.estacionamento.infra.database.models;

import java.time.ZonedDateTime;
import java.util.Objects;

public class PassageModel {
  private final Integer       id;
  private final Integer       carId;
  private final Float         fineValue;
  private final ZonedDateTime passageStartTime;
  private final ZonedDateTime passageEndTime;

  public PassageModel(
    Integer id,
    Integer carId,
    Float fineValue,
    ZonedDateTime passageStartTime,
    ZonedDateTime passageEndTime
  ) {
    this.id = id;
    this.carId = carId;
    this.fineValue = fineValue;
    this.passageStartTime = passageStartTime;
    this.passageEndTime = passageEndTime;
  }

  public Integer getId() {
    return id;
  }

  public Integer getCarId() {
    return carId;
  }

  public Float getFineValue() {
    return fineValue;
  }

  public ZonedDateTime getPassageStartTime() {
    return passageStartTime;
  }

  public ZonedDateTime getPassageEndTime() {
    return passageEndTime;
  }

  @Override public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PassageModel that = (PassageModel) o;
    return Objects.equals(id, that.id);
  }

  @Override public int hashCode() {
    return Objects.hashCode(id);
  }
}
