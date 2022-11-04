package com.ecommerce.demo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.demo.services.CloudinaryService;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {
	CloudinaryService cloudinaryService;

	@Autowired
	public ProductImageController(CloudinaryService cloudinaryService) {
		this.cloudinaryService = cloudinaryService;
	}

	@PostMapping(value = "/images")
	public ResponseEntity<List<String>> uploadMultibleImages(MultipartFile[] multipartFiles) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(cloudinaryService.uploadImages(multipartFiles));
	}

	@PostMapping(value = "")
	public ResponseEntity<String> uploadImage(MultipartFile multipartFiles) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(cloudinaryService.uploadImage(multipartFiles));
	}
}
