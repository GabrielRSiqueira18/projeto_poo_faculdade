package project.poo.estacionamento.infra.database.factories;

import project.poo.estacionamento.infra.database.models.CarModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateCarModelFactory {
  public static CarModel make(ResultSet rs) {
    try {
      return new CarModel(
        rs.getInt("id"),
        rs.getString("license_plate"),
        rs.getString("model")
      );
    } catch (SQLException e) {
      return null;
    }
  }
}
