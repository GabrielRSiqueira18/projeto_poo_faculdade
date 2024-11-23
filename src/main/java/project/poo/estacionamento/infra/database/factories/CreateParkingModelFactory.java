package project.poo.estacionamento.infra.database.factories;

import project.poo.estacionamento.infra.database.models.ParkingModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class CreateParkingModelFactory {
  public static ParkingModel make(ResultSet rs) {
    try {
      return new ParkingModel(
        rs.getInt("id"),
        rs.getString("person_name"),
        rs.getString("license_plate"),
        rs.getString("cpf"),
        rs.getString("started_date") != null ? LocalDateTime.parse(rs.getString("started_date")) : null,
        rs.getString("ended_date") != null ? LocalDateTime.parse(rs.getString("ended_date")) : null,
        rs.getInt("occupied") == 1,
        rs.getString("password_to_pay"),
        rs.getDouble("price_value"),
        rs.getInt("i"),
        rs.getInt("j")
      );
    } catch (SQLException e) {
      return null;
    }
  }
}
