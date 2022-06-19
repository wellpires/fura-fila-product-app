package br.com.furafila.productapp.service;

import br.com.furafila.productapp.dto.EditDimensionDTO;
import br.com.furafila.productapp.dto.NewDimensionDTO;

public interface DimensionService {

	Long create(NewDimensionDTO newDimensionDTO);

	void edit(EditDimensionDTO editDimensionDTO, Long dimensionId);

}
