package project.poo.estacionamento.infra.database.models;

import java.time.LocalDateTime;

public class ParkingModel {
  private Integer       id;
  private String        personName;
  private String        licensePlate;
  private String        cpf;
  private LocalDateTime dateStarted;
  private LocalDateTime dateEnded;
  private boolean       occupied;
  private String        passwordToPay;
  private Double        priceValue;
  private int           i;
  private int           j;

  public ParkingModel(
    Integer id,
    String personName,
    String licensePlate,
    String cpf,
    LocalDateTime dateStarted,
    LocalDateTime dateEnded,
    boolean occupied,
    String passwordToPay,
    Double priceValue,
    int i,
    int j
  ) {
    this.id = id;
    this.personName = personName;
    this.licensePlate = licensePlate;
    this.cpf = cpf;
    this.dateStarted = dateStarted;
    this.dateEnded = dateEnded;
    this.occupied = occupied;
    this.passwordToPay = passwordToPay;
    this.priceValue = priceValue;
    this.i = i;
    this.j = j;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public LocalDateTime getDateStarted() {
    return dateStarted;
  }

  public void setDateStarted(LocalDateTime dateStarted) {
    this.dateStarted = dateStarted;
  }

  public LocalDateTime getDateEnded() {
    return dateEnded;
  }

  public void setDateEnded(LocalDateTime dateEnded) {
    this.dateEnded = dateEnded;
  }

  public boolean isOccupied() {
    return occupied;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }

  public String getPasswordToPay() {
    return passwordToPay;
  }

  public void setPasswordToPay(String passwordToPay) {
    this.passwordToPay = passwordToPay;
  }

  public double getPriceValue() {
    return priceValue;
  }

  public void setPriceValue(double priceValue) {
    this.priceValue = priceValue;
  }

  public int getI() {
    return i;
  }

  public void setI(int i) {
    this.i = i;
  }

  public int getJ() {
    return j;
  }

  public void setJ(int j) {
    this.j = j;
  }
}
