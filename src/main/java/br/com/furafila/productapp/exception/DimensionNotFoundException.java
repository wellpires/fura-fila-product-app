package br.com.furafila.productapp.exception;

public class DimensionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6291778760838175222L;

	public DimensionNotFoundException() {
		super("Dimension not found!");
	}

}
