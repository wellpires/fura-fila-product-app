package br.com.furafila.productapp.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4259073453393477298L;

	public ProductNotFoundException() {
		super("Product not found!");
	}

}
