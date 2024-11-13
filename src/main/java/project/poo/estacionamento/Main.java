package project.poo.estacionamento;

import project.poo.estacionamento.core.Repositories;
import project.poo.estacionamento.infra.database.DbConnection;
import project.poo.estacionamento.core.factories.CreateRepositoriesFactory;
import project.poo.estacionamento.core.factories.CreateTablesFactory;
import project.poo.estacionamento.infra.database.seeds.ParkingSeed;
import project.poo.estacionamento.infra.database.seeds.UserSeed;
import project.poo.estacionamento.infra.services.*;
import project.poo.estacionamento.ui.LoginPage;

public class Main {
  private static Repositories repositories = CreateRepositoriesFactory.make();

  public static byte loginId = -1;
  public static UserServices userServices = new UserServices(repositories.getUserRepository());
  public static CarServices carServices = new CarServices(repositories.getCarRepository());
  public static PassageServices passageServices = new PassageServices(repositories.getPassageRepository(), repositories.getCarRepository());
  public static CarParkingServices carParkingServices = new CarParkingServices(repositories.getCarParkingRepository(), repositories.getCarRepository());
  public static ParkingsServices   parkingsServices = new ParkingsServices(repositories.getParkingRepository());

  public static void main(String[] args) {
    boolean deleteDb = true;
    DbConnection.connect();

    CreateTablesFactory.make(deleteDb);
    if (deleteDb) {
      UserSeed.seed();
      ParkingSeed.seed();
    }

    new LoginPage();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      DbConnection.close();
      System.out.println("Conexão com o banco de dados fechada.");
    }));
  }
}