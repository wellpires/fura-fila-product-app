package br.com.furafila.productapp.dto;

public class ProductTypeDuplicityDTO {

	private Boolean isExists;

	public ProductTypeDuplicityDTO() {
	}

	public ProductTypeDuplicityDTO(Boolean isExists) {
		this.isExists = isExists;
	}

	public Boolean getIsExists() {
		return isExists;
	}

	public void setIsExists(Boolean isExists) {
		this.isExists = isExists;
	}

}
