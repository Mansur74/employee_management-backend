package com.example.app.core.utilities.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResult<T> extends Result{
	
	T data;
	public DataResult(boolean success, String message, T data) {
		super(success, message);
		this.data = data;
	}
	
	public DataResult(boolean success, T data) {
		super(success);
		this.data = data;
	}
	
}
