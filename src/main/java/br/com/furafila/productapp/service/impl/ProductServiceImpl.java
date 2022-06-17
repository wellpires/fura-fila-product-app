package br.com.furafila.productapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.function.Product2EstablishmentProductDTOFunction;
import br.com.furafila.productapp.model.Dimension;
import br.com.furafila.productapp.model.Image;
import br.com.furafila.productapp.model.Product;
import br.com.furafila.productapp.model.ProductType;
import br.com.furafila.productapp.respository.ProductRepository;
import br.com.furafila.productapp.service.DimensionService;
import br.com.furafila.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private DimensionService dimensionService;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId) {

		List<Product> products = productRepository.listEstablishmentProducts(establishmentId);

		return products.stream().map(new Product2EstablishmentProductDTOFunction()).collect(Collectors.toList());
	}

	@Override
	public Long createProduct(NewProductDTO newProductDTO) {

		Long dimensionId = dimensionService.create(newProductDTO.getNewDimensionDTO());

		Product product = new Product();
		product.setName(newProductDTO.getProductName());
		product.setMinimumQuantity(newProductDTO.getMinimumStockQuantity());

		Dimension dimension = new Dimension();
		dimension.setId(dimensionId);
		product.setDimension(dimension);

		ProductType productType = new ProductType();
		productType.setId(newProductDTO.getProductTypeId());
		product.setProductType(productType);

		Image image = new Image();
		image.setId(newProductDTO.getImageId());
		product.setImage(image);
		return productRepository.save(product).getId();

	}

}
