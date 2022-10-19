package com.ecommerce.demo.data.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="slider")
public class SliderEntity extends BaseEntity {
	@Column(name="caption")
	private String caption;
	
	@Column(name="description")
	private String description;
	
	@Column(name="link")
	private String link;
	
	@OneToMany(mappedBy = "slider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SliderImageEntity> sliderImages;
}
