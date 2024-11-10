package project.poo.estacionamento.infra.database.repositories;

import project.poo.estacionamento.core.interfaces.RepositoryCreatorAndDeleter;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.infra.database.factories.CreateUserModelFactory;
import project.poo.estacionamento.infra.database.models.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DbConnection implements RepositoryCreatorAndDeleter {
  public boolean initialize() {
    String sql = "CREATE TABLE if NOT EXISTS users (\n" +
                 "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                 "    username VARCHAR(100) NOT NULL UNIQUE,\n" +
                 "    email VARCHAR(100) NOT NULL UNIQUE,\n" +
                 "    password VARCHAR(100) NOT NULL,\n" +
                 "    is_admin INTEGER NOT NULL DEFAULT 0\n" +
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
    String sql = "DROP TABLE if EXISTS users;";

    try (Statement stmt = conn.createStatement()) {
      stmt.execute(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Error " + e.getMessage());
      return false;
    }
  }

  public boolean create(UserModel userModel) {
    String insertSQL
      = "INSERT INTO users (username, email, password, is_admin) VALUES (?, ?, ?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
      pstmt.setString(1, userModel.getUsername());
      pstmt.setString(2, userModel.getEmail());
      pstmt.setString(3, userModel.getPassword());
      pstmt.setInt(4, userModel.isAdmin() == true ? 1 : 0);
      pstmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      return false;
    }
  }

  public List<UserModel> findMany() {
    List<UserModel> users = new ArrayList<>();
    String selectSQL = "SELECT * FROM users";

    try (Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(selectSQL)
    ) {

      System.out.println("Consultando dados da tabela 'users':");
      while (rs.next()) {
        users.add(CreateUserModelFactory.make(rs));
      }

      return users;

    } catch (SQLException e) {
      System.out.println("Erro ao consultar usuários: " + e.getMessage());
      return null;
    }
  }

  public UserModel findById(String id) {
    String selectSQL = "SELECT * FROM users WHERE id == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setString(1, id);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreateUserModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      return null;
    }
  }

  public UserModel findByEmail(String email) {
    String selectSQL = "SELECT * FROM users WHERE email == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setString(1, email);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreateUserModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      return null;
    }
  }

  public UserModel findByUsername(String username) {
    String selectSQL = "SELECT * FROM users WHERE username == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setString(1, username);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreateUserModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      return null;
    }
  }

  public UserModel findByEmailAndUsername(String username, String email) {
    String selectSQL = "SELECT * FROM users WHERE username == ? OR email == ?";

    try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
      pstmt.setString(1, username);
      pstmt.setString(2, email);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        return CreateUserModelFactory.make(rs);
      } else {
        return null;
      }
    } catch (SQLException e) {
      return null;
    }

  }
}
