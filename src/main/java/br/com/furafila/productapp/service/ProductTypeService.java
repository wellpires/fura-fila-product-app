package br.com.furafila.productapp.service;

import java.util.List;

import br.com.furafila.productapp.dto.EditProductTypeDTO;
import br.com.furafila.productapp.dto.NewProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDuplicityDTO;

public interface ProductTypeService {

	List<ProductTypeDTO> listProductTypes(Boolean activeOnly);

	void edit(EditProductTypeDTO editProductTypeDTO, Long productTypeId);

	void toggleStatus(Long productTypeId);

	void create(NewProductTypeDTO newProductTypeDTO);

	ProductTypeDuplicityDTO checkProductTypeDuplicity(String productTypeName);

}
