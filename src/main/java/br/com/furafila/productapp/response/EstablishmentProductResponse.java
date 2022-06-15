package br.com.furafila.productapp.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;

public class EstablishmentProductResponse {

	@JsonProperty("establishmentProducts")
	private List<EstablishmentProductDTO> establishmentProductDTOs;

	public EstablishmentProductResponse(List<EstablishmentProductDTO> establishmentProductDTOs) {
		this.establishmentProductDTOs = establishmentProductDTOs;
	}

	public List<EstablishmentProductDTO> getEstablishmentProductDTOs() {
		return establishmentProductDTOs;
	}

	public void setEstablishmentProductDTOs(List<EstablishmentProductDTO> establishmentProductDTOs) {
		this.establishmentProductDTOs = establishmentProductDTOs;
	}

}
