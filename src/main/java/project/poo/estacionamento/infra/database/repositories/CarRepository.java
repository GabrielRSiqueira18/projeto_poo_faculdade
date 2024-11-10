package project.poo.estacionamento.infra.database.repositories;

import project.poo.estacionamento.core.interfaces.RepositoryCreatorAndDeleter;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.infra.database.factories.CreateCarModelFactory;
import project.poo.estacionamento.infra.database.models.CarModel;
import project.poo.estacionamento.infra.database.repositories.dto.UpdateCarDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CarRepository extends DbConnection implements RepositoryCreatorAndDeleter {
  public boolean initialize() {
    String sql = "CREATE TABLE if NOT EXISTS cars (\n" +
                 "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                 "  model VARCHAR(100) NOT NULL,\n" +
                 "  license_plate VARCHAR(6) NOT NULL\n" +
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
    String sql = "DROP TABLE if EXISTS cars;";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Error " + e.getMessage());
      return false;
    }
  }

  public boolean create(CarModel carModel) {
    String insertSQL = "INSERT INTO cars (model, license_plate) VALUES (?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
      pstmt.setString(1, carModel.getModel());
      pstmt.setString(2, carModel.getLicensePlate());
      pstmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      return false;
    }
  }

  public List<CarModel> findMany() {
    List<CarModel> cars = new ArrayList<>();
    String selectSQL = "SELECT * FROM cars";

    try (Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(selectSQL)
    ) {

      System.out.println("Consultando dados da tabela 'cars':");
      while (rs.next()) {
        cars.add(CreateCarModelFactory.make(rs) != null
          ? CreateCarModelFactory.make(rs)
          : null);

        if (cars.getFirst() == null) throw new SQLException("");
      }

      return cars;

    } catch (SQLException e) {
      System.out.println("Erro ao consultar usuários: " + e.getMessage());
      return null;
    }
  }

  public CarModel findById(Integer id) {
    String selectSQL = "SELECT * FROM cars WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setInt(1, id);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreateCarModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.out.println("eihei: " + e.getMessage());
      return null;
    }
  }

  public boolean deleteById(Integer id) {
    String selectSQL = "DELETE FROM cars WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setInt(1, id);

      pstmt.executeQuery();

      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public void update(UpdateCarDto carUpdate) {
    List<String> fieldsToUpdate = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    if (carUpdate.licensePlate() != null) {
      fieldsToUpdate.add("license_plate = ?");
      values.add(carUpdate.licensePlate());
    }
    if (carUpdate.model() != null) {
      fieldsToUpdate.add("model = ?");
      values.add(carUpdate.model());
    }

    CarModel car = findById(carUpdate.carIdTarget());

    if (car == null) throw new RuntimeException("");
    String updateSQL = "UPDATE cars SET " +
                       String.join(", ", fieldsToUpdate) +
                       " WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
      // Define os valores dos parâmetros dinamicamente
      for (int i = 0; i < values.size(); i++) {
        pstmt.setObject(i + 1, values.get(i));
      }
      pstmt.setInt(
        values.size() + 1,
        carUpdate.carIdTarget()
      );  // Define o ID do carro no último parâmetro

      int rowsAffected = pstmt.executeUpdate();
      System.out.println("Rows affected: " + rowsAffected);
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }

  }
}
