package project.poo.estacionamento.infra.database.factories;

import project.poo.estacionamento.infra.database.models.PassageModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class CreatePassageModelFactory {
  public static PassageModel make(ResultSet rs) {
    try {
      return new PassageModel(
        rs.getInt("id"),
        rs.getInt("id_car"),
        rs.getFloat("fine_value"),
        ZonedDateTime.parse(rs.getString("passage_start_time")),
        rs.getString("passage_end_time") == null ? null : ZonedDateTime.parse(rs.getString("passage_end_time"))
      );
    } catch (SQLException e) {
      return null;
    }
  }
}
