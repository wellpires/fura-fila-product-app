package br.com.furafila.productapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.furafila.productapp.model.converter.Bit2BooleanConverter;

@Table(name = "TIPO_PRODUTO")
@Entity
public class ProductType implements Serializable {

	private static final long serialVersionUID = -8329644398690903035L;

	@Id
	@Column(name = "id_tipo_produto", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_produto_desc")
	private String name;

	@Column(name = "status", columnDefinition = "int4")
	@Convert(converter = Bit2BooleanConverter.class)
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
