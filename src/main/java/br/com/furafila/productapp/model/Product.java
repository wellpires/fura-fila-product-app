package br.com.furafila.productapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.furafila.productapp.model.converter.Bit2BooleanConverter;

@Table(name = "PRODUTO")
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = -5079737368349609365L;

	@Id
	@Column(name = "id_produto", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "produto_desc", nullable = false)
	private String name;

	@Column(name = "valor_unitario", columnDefinition = "numeric(5, 2)")
	private Double unitPrice;

	@Column(name = "qtdminima", columnDefinition = "int4")
	private Long minimumQuantity;

	@Column(name = "qtdmaxima", columnDefinition = "int4")
	private Long maximumQuantity;

	@Column(name = "status", columnDefinition = "int4")
	@Convert(converter = Bit2BooleanConverter.class)
	private Boolean status;

	@OneToOne
	@JoinColumn(name = "id_dimensao_fk", nullable = false, columnDefinition = "int4")
	private Dimension dimension;

	@OneToOne
	@JoinColumn(name = "id_imagem_fk", nullable = false, columnDefinition = "int4")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "id_tipo_produto_fk", nullable = false, columnDefinition = "int4")
	private ProductType productType;

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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getMinimumQuantity() {
		return minimumQuantity;
	}

	public void setMinimumQuantity(Long minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}

	public Long getMaximumQuantity() {
		return maximumQuantity;
	}

	public void setMaximumQuantity(Long maximumQuantity) {
		this.maximumQuantity = maximumQuantity;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
