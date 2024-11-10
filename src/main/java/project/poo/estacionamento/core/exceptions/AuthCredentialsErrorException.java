package project.poo.estacionamento.core.exceptions;

public class AuthCredentialsErrorException extends RuntimeException {
  public AuthCredentialsErrorException() {
    super("User credentials error");
  }
}
