package br.com.furafila.productapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.productapp.dao.ProductDAO;
import br.com.furafila.productapp.dto.EditProductDTO;
import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.dto.NewProductDTO;
import br.com.furafila.productapp.dto.EditProductUnitPriceDTO;
import br.com.furafila.productapp.exception.ProductNotFoundException;
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

	@Autowired
	private ProductDAO productoDAO;

	@Override
	public List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId) {
		return productoDAO.listEstablishmentProducts(establishmentId);
	}

	@Override
	public Long createProduct(NewProductDTO newProductDTO) {

		Long dimensionId = dimensionService.create(newProductDTO.getNewDimensionDTO());

		Product product = new Product();
		product.setName(newProductDTO.getName());
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

	@Override
	public void edit(EditProductDTO editProductDTO, Long productId) {

		Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
		dimensionService.edit(editProductDTO.getEditDimensionDTO(), product.getDimension().getId());

		product.setName(editProductDTO.getName());
		product.setMinimumQuantity(editProductDTO.getMinimumStockQuantity());

		ProductType productType = new ProductType();
		productType.setId(editProductDTO.getProductTypeId());
		product.setProductType(productType);

		productRepository.save(product);

	}

	@Override
	public void toggleProductStatus(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

		product.setStatus(!product.getStatus());

		productRepository.save(product);

	}

	@Override
	public void editUnitPrice(EditProductUnitPriceDTO newUnitPriceDTO, Long productId) {

		Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
		product.setUnitPrice(newUnitPriceDTO.getUnitPrice());

		productRepository.save(product);

	}

}
