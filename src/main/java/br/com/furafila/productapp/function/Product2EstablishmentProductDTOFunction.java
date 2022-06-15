package br.com.furafila.productapp.function;

import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.furafila.productapp.builder.EstablishmentProductDTOBuilder;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDimensionDTO;
import br.com.furafila.productapp.dto.EstablishmentProductTypeDTO;
import br.com.furafila.productapp.model.Product;

public class Product2EstablishmentProductDTOFunction implements Function<Product, EstablishmentProductDTO> {

//TODO stockQuantity is missing!
	@Override
	public EstablishmentProductDTO apply(Product product) {

		EstablishmentProductTypeDTO establishmentProductTypeDTO = new EstablishmentProductTypeDTO();
		establishmentProductTypeDTO.setId(null);
		establishmentProductTypeDTO.setName(null);

		EstablishmentProductDimensionDTO establishmentProductDimensionDTO = new EstablishmentProductDimensionDTO();
		establishmentProductDimensionDTO.setId(null);
		establishmentProductDimensionDTO.setHeight(null);
		establishmentProductDimensionDTO.setWidth(null);
		establishmentProductDimensionDTO.setLength(null);

//		return new EstablishmentProductDTOBuilder().productId(product.getId()).productName(product.getName())
//				.unitPrice(product.getUnitPrice()).status(product.getStatus()).imageId(product.getImage().getId())
//				.stockMinimumQuantity(product.getMinimumQuantity()).stockQuantity(123l)
//				.establishmentProductTypeDTO(establishmentProductTypeDTO)
//				.establishmentProductDimensionDTO(establishmentProductDimensionDTO).build();
		return null;
	}

}
