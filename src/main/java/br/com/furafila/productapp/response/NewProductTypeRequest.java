package br.com.furafila.productapp.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.NewProductTypeDTO;
import br.com.furafila.productapp.util.Messages;

public class NewProductTypeRequest {

	@JsonProperty("productType")
	@NotNull(message = Messages.NEW_PRODUCT_TYPE_INFORMATION_IS_REQUIRED)
	@Valid
	private NewProductTypeDTO newProductTypeDTO;

	public NewProductTypeDTO getNewProductTypeDTO() {
		return newProductTypeDTO;
	}

	public void setNewProductTypeDTO(NewProductTypeDTO newProductTypeDTO) {
		this.newProductTypeDTO = newProductTypeDTO;
	}

}
