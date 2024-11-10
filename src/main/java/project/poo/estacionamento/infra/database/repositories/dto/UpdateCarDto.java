package project.poo.estacionamento.infra.database.repositories.dto;

public record UpdateCarDto(
  Integer carIdTarget,
  String licensePlate,
  String model
) {
}
