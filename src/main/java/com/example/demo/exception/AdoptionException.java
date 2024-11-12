package com.example.demo.exception;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

public class AdoptionException extends RuntimeException{
	
	public AdoptionException (String message) {
		super(message);
	}
	
}
