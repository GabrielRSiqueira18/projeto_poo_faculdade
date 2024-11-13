package project.poo.estacionamento.core.factories;

import project.poo.estacionamento.core.interfaces.RepositoryCreatorAndDeleter;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.infra.database.repositories.*;

public class CreateTablesFactory {
  private static void createTable(RepositoryCreatorAndDeleter repository, boolean delete) {
    if (delete) {
      if (!repository.drop()) {
        System.out.println("Table cannot delete data!");
        DbConnection.close();
        System.out.println("Closing app");
        System.exit(0);
      }
    }

    if (!repository.initialize()) {
      System.out.println("Table users cannot create!");
      DbConnection.close();
      System.out.println("Closing app");
      System.exit(0);
    }
  }

  public static void make(
    boolean delete
  ) {
    createTable(new UserRepository(), delete);
    createTable(new CarParkingRepository(), delete);
    createTable(new PassageRepository(), delete);
    createTable(new CarRepository(), delete);
    createTable(new ParkingRepository(), delete);
  }
}