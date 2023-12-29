package com.example.app.core.utilities.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	boolean success;
	String message;
	
	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public Result(boolean success) {
		this.success = success;
		this.message = null;
	}
	
	
	
}
