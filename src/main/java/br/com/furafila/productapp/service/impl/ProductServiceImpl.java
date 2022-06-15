package br.com.furafila.productapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.productapp.dto.EstablishmentProductDTO;
import br.com.furafila.productapp.model.Product;
import br.com.furafila.productapp.respository.ProductRespository;
import br.com.furafila.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRespository productRespository;

	@Override
	public List<EstablishmentProductDTO> listEstablishmentProducts(Long establishmentId) {
		
		List<Product> products = productRespository.listEstablishmentProducts(establishmentId);
		
//		return products.stream().map(item);
		return null;
	}

}
