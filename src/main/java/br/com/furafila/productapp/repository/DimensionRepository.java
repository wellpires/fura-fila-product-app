package br.com.furafila.productapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.furafila.productapp.model.Dimension;

public interface DimensionRepository extends JpaRepository<Dimension, Long> {

}
