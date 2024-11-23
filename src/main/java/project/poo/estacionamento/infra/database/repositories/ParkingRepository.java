package project.poo.estacionamento.infra.database.repositories;

import project.poo.estacionamento.core.interfaces.RepositoryCreatorAndDeleter;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.infra.database.factories.CreateParkingModelFactory;
import project.poo.estacionamento.infra.database.models.ParkingModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkingRepository extends DbConnection implements RepositoryCreatorAndDeleter {
  public boolean initialize() {
    String sql = "CREATE TABLE if NOT EXISTS parkings (\n" +
                 "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                 "  person_name VARCHAR(100),\n" +
                 "  license_plate VARCHAR(7),\n" +
                 "  cpf VARCHAR(11),\n" +
                 "  started_date TIMESTAMP WITH TIME ZONE,\n" +
                 "  ended_date TIMESTAMP WITH TIME ZONE,\n" +
                 "  occupied INTEGER NOT NULL,\n" +
                 "  password_to_pay VARCHAR(5),\n" +
                 "  price_value DOUBLE,\n" +
                 "  i INTEGER,\n" +
                 "  j INTEGER\n" +
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
    String sql = "DROP TABLE if EXISTS parkings;";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Error " + e.getMessage());
      return false;
    }
  }

  public boolean create(ParkingModel parkingModel) {
    String insertSQL
      = "INSERT INTO parkings (person_name, license_plate, cpf, started_date, ended_date, occupied, password_to_pay, price_value, i, j) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
      pstmt.setString(1, parkingModel.getPersonName());
      pstmt.setString(2, parkingModel.getLicensePlate());
      pstmt.setString(3, parkingModel.getCpf());
      pstmt.setObject(4, parkingModel.getDateStarted());
      pstmt.setObject(5, parkingModel.getDateEnded());
      pstmt.setInt(6, parkingModel.isOccupied() ? 1 : 0);
      pstmt.setString(7, parkingModel.getPasswordToPay());
      pstmt.setDouble(8, parkingModel.getPriceValue());
      pstmt.setInt(9, parkingModel.getI());
      pstmt.setInt(10, parkingModel.getJ());
      pstmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public ParkingModel[][] findMany() {
    ParkingModel[][] parkings = new ParkingModel[5][5];
    String selectSQL = "SELECT * FROM parkings";

    try (Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(selectSQL)
    ) {

      System.out.println("Consultando dados da tabela 'parkings':");
      int i = 0;
      int j = 0;
      while (rs.next()) {
        if (j == 5) {
          j = 0;
          i += 1;
        }

        parkings[i][j] = CreateParkingModelFactory.make(rs);
        j += 1;
      }

      return parkings;

    } catch (SQLException e) {
      System.out.println("Erro ao consultar usuários: " + e.getMessage());
      return null;
    }
  }

}
