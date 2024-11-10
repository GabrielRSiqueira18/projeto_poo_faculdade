package project.poo.estacionamento.infra.database.models;

import java.util.Objects;

public class UserModel {
  private final Integer  id;
  private final String  username;
  private final String  email;
  private final String  password;
  private final boolean isAdmin;

  public UserModel(
    Integer id,
    String username,
    String email,
    String password,
    boolean isAdmin
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  @Override public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserModel userModel = (UserModel) o;
    return Objects.equals(id, userModel.id);
  }

  @Override public int hashCode() {
    return Objects.hashCode(id);
  }
}
