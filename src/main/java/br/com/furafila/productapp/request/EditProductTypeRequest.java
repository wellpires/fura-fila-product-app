package br.com.furafila.productapp.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.EditProductTypeDTO;
import br.com.furafila.productapp.util.Messages;

public class EditProductTypeRequest {

	@JsonProperty("productType")
	@NotNull(message = Messages.EDIT_PRODUCT_TYPE_INFORMATION_IS_REQUIRED)
	@Valid
	private EditProductTypeDTO editProductTypeDTO;

	public EditProductTypeDTO getEditProductTypeDTO() {
		return editProductTypeDTO;
	}

	public void setEditProductTypeDTO(EditProductTypeDTO editProductTypeDTO) {
		this.editProductTypeDTO = editProductTypeDTO;
	}

}
