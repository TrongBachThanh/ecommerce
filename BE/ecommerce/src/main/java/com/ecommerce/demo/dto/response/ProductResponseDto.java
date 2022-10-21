package com.ecommerce.demo.dto.response;

public class ProductResponseDto {
	private Long id;
	private String name;
	private String code;
	private String description;
	private boolean isFeatured;
	private boolean isNew;
	private Double price;
	private String thumbnal;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFeatured() {
		return isFeatured;
	}

	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getThumbnal() {
		return thumbnal;
	}

	public void setThumbnal(String thumbnal) {
		this.thumbnal = thumbnal;
	}

}
