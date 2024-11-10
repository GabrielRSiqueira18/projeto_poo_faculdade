package project.poo.estacionamento.infra.database.repositories;

import project.poo.estacionamento.core.interfaces.RepositoryCreatorAndDeleter;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.infra.database.factories.CreatePassageModelFactory;
import project.poo.estacionamento.infra.database.models.PassageModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassageRepository extends DbConnection implements RepositoryCreatorAndDeleter {
  public boolean initialize() {
    String sql = "CREATE TABLE IF NOT EXISTS passages (\n" +
                 "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                 "    id_car INTEGER NOT NULL,\n" +
                 "    fine_value FLOAT,\n" +
                 "    passage_start_time TIMESTAMP WITH TIME ZONE NOT NULL,\n" +
                 "    passage_end_time TIMESTAMP WITH TIME ZONE,\n" +
                 "    FOREIGN KEY (id_car) REFERENCES cars(id) \n" +
                 ");";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Error " + e.getMessage());
      return false;
    }
  }

  public boolean drop() {
    String sql = "DROP TABLE passages;";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Error " + e.getMessage());
      return false;
    }
  }

  public boolean create(PassageModel passageModel) {
    String insertSQL =
      "INSERT INTO passages (id_car, fine_value, passage_start_time, passage_end_time) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
      pstmt.setInt(1, passageModel.getCarId());
      pstmt.setFloat(2, passageModel.getFineValue());
      pstmt.setObject(3, passageModel.getPassageStartTime());
      pstmt.setObject(4, passageModel.getPassageEndTime());
      pstmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public List<PassageModel> findMany() {
    List<PassageModel> passages = new ArrayList<>();
    String selectSQL = "SELECT * FROM passages";

    try (Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(selectSQL)
    ) {

      System.out.println("Consultando dados da tabela 'passages':");
      while (rs.next()) {
        passages.add(CreatePassageModelFactory.make(rs) != null ? CreatePassageModelFactory.make(rs) : null);

        if (passages.getFirst() == null) throw new SQLException("");
      }

      return passages;

    } catch (SQLException e) {
      System.out.println("Erro ao consultar usuários: " + e.getMessage());
      return null;
    }
  }

  public PassageModel findById(Integer id) {
    String selectSQL = "SELECT * FROM passages WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setInt(1, id);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreatePassageModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      return null;
    }
  }

  public boolean deleteById(Integer id) {
    String selectSQL = "DELETE FROM passages WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setInt(1, id);

      pstmt.executeUpdate();

      return true;
    } catch (SQLException e) {
      return false;
    }
  }
}
