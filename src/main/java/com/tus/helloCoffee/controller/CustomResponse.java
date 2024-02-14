package com.tus.helloCoffee.controller;

public class CustomResponse {
  private int status;
  private String message;

  // getters and setters
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
