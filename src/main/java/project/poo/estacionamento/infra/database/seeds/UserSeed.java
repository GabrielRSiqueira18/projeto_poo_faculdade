package project.poo.estacionamento.infra.database.seeds;

import project.poo.estacionamento.infra.database.models.UserModel;
import project.poo.estacionamento.infra.database.repositories.UserRepository;

public class UserSeed {
  public static void seed() {
    UserRepository userRepository = new UserRepository();

    for (int i = 0; i < 2; i++) {
      userRepository.create(new UserModel(
        null,
        i == 0 ? "client" : "admin",
        i == 0 ? "client@email.com" : "admin@email.com",
        i == 0 ? "client" : "admin",
        i == 0 ? false : true
      ));
    }
  }
}
