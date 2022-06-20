package br.com.furafila.productapp.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.EditProductTypeDTO;

public class EditProductTypeRequest {

	@JsonProperty("productType")
	private EditProductTypeDTO editProductTypeDTO;

	public EditProductTypeDTO getEditProductTypeDTO() {
		return editProductTypeDTO;
	}

	public void setEditProductTypeDTO(EditProductTypeDTO editProductTypeDTO) {
		this.editProductTypeDTO = editProductTypeDTO;
	}

}
