package project.poo.estacionamento.infra.database.repositories;

import project.poo.estacionamento.core.interfaces.RepositoryCreatorAndDeleter;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.infra.database.factories.CreateCarParkingModelFactory;
import project.poo.estacionamento.infra.database.models.CarParkingModel;
import project.poo.estacionamento.infra.database.repositories.dto.CreateCarParkingDto;
import project.poo.estacionamento.infra.database.repositories.dto.UpdateCarParkingDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarParkingRepository extends DbConnection implements RepositoryCreatorAndDeleter {
  public boolean initialize() {
    String sql = "CREATE TABLE if NOT EXISTS cars_parkings (\n" +
                 "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                 "  id_car INTEGER NOT NULL,\n" +
                 "  price_value FLOAT NOT NULL,\n" +
                 "  interest FLOAT,\n" +
                 "  start_entry_time TIMESTAMP WITH TIME ZONE NOT NULL,\n" +
                 "  end_entry_time TIMESTAMP WITH TIME ZONE,\n" +
                 "  FOREIGN KEY (id_car) REFERENCES cars(id) \n" +
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
    String sql = "DROP TABLE if EXISTS cars_parkings;";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Error " + e.getMessage());
      return false;
    }
  }

  public boolean create(CreateCarParkingDto carParking) {
    String insertSQL
      = "INSERT INTO cars_parkings (id_car, price_value, interest, start_entry_time, end_entry_time) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
      pstmt.setInt(1, carParking.idCar());
      pstmt.setFloat(2, carParking.priceValue());
      pstmt.setFloat(3, carParking.interest());
      pstmt.setObject(4, carParking.startEntryTime());
      pstmt.setObject(5, null);
      pstmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public List<CarParkingModel> findMany() {
    List<CarParkingModel> carParking = new ArrayList<>();
    String selectSQL = "SELECT * FROM cars_parkings";

    try (Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(selectSQL)
    ) {

      System.out.println("Consultando dados da tabela 'car parking':");
      while (rs.next()) {
        carParking.add(CreateCarParkingModelFactory.make(rs) != null
          ? CreateCarParkingModelFactory.make(rs)
          : null);

        if (carParking.getFirst() == null) throw new SQLException("");
      }

      return carParking;

    } catch (SQLException e) {
      System.out.println("Erro ao consultar usuários: " + e.getMessage());
      return null;
    }
  }

  public CarParkingModel findById(Integer id) {
    String selectSQL = "SELECT * FROM cars_parkings WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setInt(1, id);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreateCarParkingModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      return null;
    }
  }

  public boolean deleteById(Integer id) {
    String selectSQL = "DELETE FROM cars_parkings WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setInt(1, id);

      pstmt.executeQuery();

      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public void update(UpdateCarParkingDto carParkingUpdate) {
    if (findById(carParkingUpdate.idTarget()) == null) throw new RuntimeException("");

    String updateSQL = "UPDATE cars_parking SET " + "end_entry_time = ?" + " WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
      pstmt.setString(1, carParkingUpdate.endEntryTime().toString());
      pstmt.setInt(2, carParkingUpdate.idTarget());  // Define o ID do carro no último parâmetro

      int rowsAffected = pstmt.executeUpdate();
      System.out.println("Rows affected: " + rowsAffected);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

  }
}
