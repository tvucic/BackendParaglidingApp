package life.josip.vucic.paragliding.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

  private static final long serialVersionUID = 1L;
  private long id; // Autoincremented id from database
  private String userId; // public id which is turned to mobile application
  private String firstName; // Provided by user
  private String lastName; // Provided by user
  private String email; // Provided by user
  private String password; // Provided by user
  private String encriptedPassword; // encrypted password stored in database
  private String emailVerificationToken;
  private Boolean emailVerificationStatus = false;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEncriptedPassword() {
    return encriptedPassword;
  }

  public void setEncriptedPassword(String encriptedPassword) {
    this.encriptedPassword = encriptedPassword;
  }

  public String getEmailVerificationToken() {
    return emailVerificationToken;
  }

  public void setEmailVerificationToken(String emailVerificationToken) {
    this.emailVerificationToken = emailVerificationToken;
  }

  public Boolean getEmailVerificationStatus() {
    return emailVerificationStatus;
  }

  public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
    this.emailVerificationStatus = emailVerificationStatus;
  }

}