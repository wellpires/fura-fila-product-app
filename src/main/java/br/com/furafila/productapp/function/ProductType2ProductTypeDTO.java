package br.com.furafila.productapp.function;

import java.util.function.Function;

import br.com.furafila.productapp.dto.ProductTypeDTO;
import br.com.furafila.productapp.model.ProductType;

public class ProductType2ProductTypeDTO implements Function<ProductType, ProductTypeDTO> {

	@Override
	public ProductTypeDTO apply(ProductType productType) {
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setId(productType.getId());
		productTypeDTO.setName(productType.getName());
		productTypeDTO.setStatus(productType.getStatus());
		return productTypeDTO;
	}

}
