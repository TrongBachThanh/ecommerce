package com.ecommerce.demo.services;

public interface AccountAdminService {
	
	public void lockUserById(Long userId) ;
	
	public void unlockUserById(Long userId);
}

