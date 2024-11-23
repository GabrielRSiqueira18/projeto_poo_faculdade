package project.poo.estacionamento.infra.database.factories;

import project.poo.estacionamento.infra.database.models.CarParkingModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CreateCarParkingModelFactory {
  public static CarParkingModel make(ResultSet rs) {
    try {
      return new CarParkingModel(
        rs.getInt("id"),
        rs.getInt("id_car"),
        rs.getFloat("price_value"),
        rs.getFloat("interest"),
        rs.getString("start_entry_time") != null ? ZonedDateTime.parse(rs.getString("start_entry_time")) : null,
        rs.getString("end_entry_time") != null ? ZonedDateTime.parse(rs.getString("end_entry_time")) : null
      );
    } catch (SQLException e) {
      System.out.println("iewfgiewngfien");
      System.out.println("erorr: " + e.getMessage());
      return null;
    }
  }
}
