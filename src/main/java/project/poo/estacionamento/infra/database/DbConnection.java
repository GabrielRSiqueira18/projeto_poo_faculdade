package project.poo.estacionamento.infra.database;


import java.sql.*;

public abstract class DbConnection {
  protected static Connection conn;     // Database connection

  public static void connect() {
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:db/todo_with_recognition.db");

      try (Statement stmt = conn.createStatement()) {
        stmt.execute("PRAGMA foreign_keys = ON;");
      }

      System.out.println("Connection to SQLite has been established.");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void close() {
    try {
      if (conn != null) {
        conn.close();
        System.out.println("Connection to SQLite closed.");
      }
    } catch (SQLException e) {
      System.out.println("Error closing SQLite connection: " + e.getMessage());
    }
  }
}
