package com.example.demo.exception;

public class UnauthorizedException extends Exception {

  //自定義非授權例外
  public UnauthorizedException(String message) {
    super(message);
  }


}
