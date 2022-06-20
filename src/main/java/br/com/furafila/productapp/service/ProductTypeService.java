package br.com.furafila.productapp.service;

import java.util.List;

import br.com.furafila.productapp.dto.EditProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDTO;

public interface ProductTypeService {

	List<ProductTypeDTO> listProductTypes(Boolean activeOnly);

	void edit(EditProductTypeDTO editProductTypeDTO, Long productTypeId);

	void toggleStatus(Long productTypeId);

}
