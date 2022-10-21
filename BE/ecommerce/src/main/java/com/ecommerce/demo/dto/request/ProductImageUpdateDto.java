package com.ecommerce.demo.dto.request;

public class ProductImageUpdateDto {
	private Long id;
	private String file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
