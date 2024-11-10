package project.poo.estacionamento.infra.database.repositories.dto;

import java.time.ZonedDateTime;

public record CreateCarParkingDto(
  Integer idCar, float priceValue, float interest, ZonedDateTime startEntryTime
) {}
