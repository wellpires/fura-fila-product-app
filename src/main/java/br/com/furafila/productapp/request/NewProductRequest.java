package br.com.furafila.productapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.util.Messages;

public class NewProductRequest {

	@JsonProperty("product")
	@Valid
	@NotNull(message = Messages.NEW_PRODUCT_INFORMATION_REQUIRED)
	private NewProductDTO newProductDTO;

	public NewProductDTO getNewProductDTO() {
		return newProductDTO;
	}

	public void setNewProductDTO(NewProductDTO newProductDTO) {
		this.newProductDTO = newProductDTO;
	}

}
