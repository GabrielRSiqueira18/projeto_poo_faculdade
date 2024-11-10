package project.poo.estacionamento.infra.services;

import project.poo.estacionamento.core.exceptions.AuthCredentialsErrorException;
import project.poo.estacionamento.infra.database.models.UserModel;
import project.poo.estacionamento.infra.database.repositories.UserRepository;
import project.poo.estacionamento.infra.services.dto.AuthUserDto;

import static project.poo.estacionamento.Main.loginId;

public class UserServices {
  private final UserRepository userRepository;

  public UserServices(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private boolean isValidData(String username, String password, String email) {
    return !(username.isBlank() || password.isBlank() || email.isBlank());
  }

  private boolean isValidData(String username, String password) {
    return !(username.isBlank() || password.isBlank());
  }

  public void create(UserModel userModel) {
    if (!isValidData(
      userModel.getUsername(),
      userModel.getEmail(),
      userModel.getPassword()
    )) throw new RuntimeException("Invalid information");

    if (userRepository.findByEmailAndUsername(
      userModel.getUsername(),
      userModel.getEmail()
    ) != null) throw new RuntimeException("User already exists.");

    if (!userRepository.create(userModel))
      throw new RuntimeException("Error to create user.");
  }

  public void auth(AuthUserDto authUserDto) {
    if (!isValidData(authUserDto.email(), authUserDto.password()))
      throw new RuntimeException("Invalid information");

    UserModel userTarget
      = userRepository.findByEmail(authUserDto.email());

    if (userTarget == null) throw new AuthCredentialsErrorException();
    if (!userTarget.getPassword().equals(authUserDto.password()))
      throw new AuthCredentialsErrorException();

    if (userTarget.isAdmin()) loginId = 2;
    else loginId = 1;
  }

  public UserModel findById(String id) {
    return userRepository.findById(id);
  }
}
