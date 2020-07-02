package edu.pdx.cs410J.huanhua;

public class UnsupportedGenderException extends RuntimeException {
	UnsupportedGenderException(String genderString) {
		super(genderString);
	}
}
