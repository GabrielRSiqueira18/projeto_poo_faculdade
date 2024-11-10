package project.poo.estacionamento.infra.database.repositories.dto;

import java.time.ZonedDateTime;

public record UpdateCarParkingDto(Integer idTarget, ZonedDateTime endEntryTime) {}
