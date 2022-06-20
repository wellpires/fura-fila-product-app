package br.com.furafila.productapp.exception;

public class ProductTypeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1007865351465055784L;

	public ProductTypeNotFoundException() {
		super("Product Type not found!");
	}

}
