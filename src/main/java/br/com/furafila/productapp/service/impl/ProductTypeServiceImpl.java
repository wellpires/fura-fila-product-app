package br.com.furafila.productapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.productapp.dto.EditProductTypeDTO;
import br.com.furafila.productapp.dto.NewProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDTO;
import br.com.furafila.productapp.dto.ProductTypeDuplicityDTO;
import br.com.furafila.productapp.exception.ProductTypeNotFoundException;
import br.com.furafila.productapp.function.ProductType2ProductTypeDTO;
import br.com.furafila.productapp.model.ProductType;
import br.com.furafila.productapp.repository.ProductTypeRepository;
import br.com.furafila.productapp.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public List<ProductTypeDTO> listProductTypes(Boolean activeOnly) {

		if (activeOnly) {
			return productTypeRepository.findByStatusIsTrue().stream().map(new ProductType2ProductTypeDTO())
					.collect(Collectors.toList());
		}

		return productTypeRepository.findAll().stream().map(new ProductType2ProductTypeDTO())
				.collect(Collectors.toList());
	}

	@Override
	public void edit(EditProductTypeDTO editProductTypeDTO, Long productTypeId) {

		ProductType productType = productTypeRepository.findById(productTypeId)
				.orElseThrow(ProductTypeNotFoundException::new);

		productType.setName(editProductTypeDTO.getName());

		productTypeRepository.save(productType);

	}

	@Override
	public void toggleStatus(Long productTypeId) {

		ProductType productType = productTypeRepository.findById(productTypeId)
				.orElseThrow(ProductTypeNotFoundException::new);
		productType.setStatus(!productType.getStatus());

		productTypeRepository.save(productType);
	}

	@Override
	public void create(NewProductTypeDTO newProductTypeDTO) {

		ProductType productType = new ProductType();
		productType.setName(newProductTypeDTO.getName());
		productType.setStatus(Boolean.TRUE);

		productTypeRepository.save(productType);

	}

	@Override
	public ProductTypeDuplicityDTO checkProductTypeDuplicity(String productTypeName) {

		Boolean isExists = productTypeRepository.existsByNameIgnoreCase(productTypeName);

		return new ProductTypeDuplicityDTO(isExists);
	}

}
