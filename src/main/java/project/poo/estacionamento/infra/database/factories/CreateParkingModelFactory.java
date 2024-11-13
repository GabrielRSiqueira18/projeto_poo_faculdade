package project.poo.estacionamento.infra.database.factories;

import project.poo.estacionamento.infra.database.models.ParkingModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateParkingModelFactory {
  public static ParkingModel make(ResultSet rs) {
    try {
      return new ParkingModel(
        rs.getInt("id"),
        rs.getString("person_name"),
        rs.getString("license_plate"),
        rs.getString("cpf"),
        LocalDateTime.parse(rs.getString("started_date")),
        LocalDateTime.parse(rs.getString("ended_date")),
        rs.getInt("occupied") == 1 ? true : false,
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
