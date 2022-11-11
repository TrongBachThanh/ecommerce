package com.ecommerce.demo.dto.request;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryUpdateDto {
	private Long id;
	private String name;
	private String code;

	@Override
	public int hashCode() {
		return Objects.hash(code, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryUpdateDto other = (CategoryUpdateDto) obj;
		return Objects.equals(code, other.code) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
