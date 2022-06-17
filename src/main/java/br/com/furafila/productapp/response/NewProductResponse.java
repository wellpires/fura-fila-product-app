package br.com.furafila.productapp.response;

public class NewProductResponse {

	private Long id;

	public NewProductResponse() {
	}

	public NewProductResponse(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
