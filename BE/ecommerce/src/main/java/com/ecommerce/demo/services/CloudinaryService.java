package com.ecommerce.demo.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	public String uploadImage(MultipartFile multipartFile) throws IOException;
	
	public List<String> uploadImages(MultipartFile[] multipartFile) throws IOException;
}
