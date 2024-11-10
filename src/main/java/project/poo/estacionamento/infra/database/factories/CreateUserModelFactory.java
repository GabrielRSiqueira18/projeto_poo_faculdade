package project.poo.estacionamento.infra.database.factories;

import project.poo.estacionamento.infra.database.models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUserModelFactory {
  public static UserModel make(ResultSet rs) {
    try {
      return new UserModel(
        rs.getInt("id"),
        rs.getString("username"),
        rs.getString("email"),
        rs.getString("password"),
        rs.getInt("is_admin") == 1
      );
    } catch (SQLException e) {
      return null;
    }
  }
}
