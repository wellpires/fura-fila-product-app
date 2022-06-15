package br.com.furafila.productapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.productapp.dto.NewDimensionDTO;
import br.com.furafila.productapp.model.Dimension;
import br.com.furafila.productapp.repository.DimensionRepository;
import br.com.furafila.productapp.service.DimensionService;

@Service
public class DimensionServiceImpl implements DimensionService {

	@Autowired
	private DimensionRepository dimensionRepository;

	@Override
	public Long create(NewDimensionDTO newDimensionDTO) {

		Dimension dimension = new Dimension();
		dimension.setHeight(newDimensionDTO.getHeight());
		dimension.setWidth(newDimensionDTO.getWidth());
		dimension.setLength(newDimensionDTO.getLength());

		return dimensionRepository.save(dimension).getId();
	}

}
