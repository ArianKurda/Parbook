package de.hdm.ITProjekt.shared;

import java.io.Serializable;

public class NotLoggedInException extends Exception implements Serializable {

  public NotLoggedInException() {
    super();
  }

  public NotLoggedInException(String message) {
    super(message);
  }

}
