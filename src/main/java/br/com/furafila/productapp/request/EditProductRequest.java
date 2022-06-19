package br.com.furafila.productapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.EditProductDTO;
import br.com.furafila.productapp.util.Messages;

public class EditProductRequest {

	@JsonProperty("product")
	@NotNull(message = Messages.EDIT_PRODUCT_INFORMATION_REQUIRED)
	@Valid
	private EditProductDTO editProductDTO;

	public EditProductDTO getEditProductDTO() {
		return editProductDTO;
	}

	public void setEditProductDTO(EditProductDTO editProductDTO) {
		this.editProductDTO = editProductDTO;
	}

}
