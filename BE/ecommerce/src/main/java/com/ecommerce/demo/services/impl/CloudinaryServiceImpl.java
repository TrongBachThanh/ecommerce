package com.ecommerce.demo.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.demo.services.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	Cloudinary cloudinary;

	@Autowired
	public CloudinaryServiceImpl(Cloudinary cloudinary) {

	}

	@Override
	public String uploadImage(MultipartFile multipartFile) throws IOException {
		if (multipartFile == null) {
			return null;
		}
		@SuppressWarnings("rawtypes")
		Map cloudinaryApi = cloudinary.uploader().upload(multipartFile.getBytes(),
				ObjectUtils.asMap("resource_type", "auto"));
		String url = (String) cloudinaryApi.get("secure_url");
		return url;

	}

	@Override
	public List<String> uploadImages(MultipartFile[] multipartFile) throws IOException {

		if (multipartFile == null) {
			return null;
		}
		List<String> urls = new ArrayList<>();
		for (MultipartFile image : multipartFile) {
			@SuppressWarnings("rawtypes")
			Map cloudinaryApi = cloudinary.uploader().upload(image.getBytes(),
					ObjectUtils.asMap("resource_type", "auto"));
			urls.add((String) cloudinaryApi.get("secure_url"));
		}
		return urls;
	}

}
