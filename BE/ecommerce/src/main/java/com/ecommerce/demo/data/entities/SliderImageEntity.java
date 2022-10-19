package com.ecommerce.demo.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="slider_image")
public class SliderImageEntity extends BaseEntity{
	@Column(name="file")
	private String file;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="slider_id")
	private SliderEntity slider;
}
