package br.com.furafila.productapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.furafila.productapp.dto.EditDimensionDTO;
import br.com.furafila.productapp.dto.NewDimensionDTO;
import br.com.furafila.productapp.exception.DimensionNotFoundException;
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

	@Override
	public void edit(EditDimensionDTO editDimensionDTO, Long dimensionId) {

		Dimension dimension = dimensionRepository.findById(dimensionId).orElseThrow(DimensionNotFoundException::new);
		dimension.setHeight(editDimensionDTO.getHeight());
		dimension.setWidth(editDimensionDTO.getWidth());
		dimension.setLength(editDimensionDTO.getLength());

		dimensionRepository.save(dimension);

	}

}
