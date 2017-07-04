package com.srm.services.rest.services;

import java.util.List;

import com.srm.services.entity.Category;
import com.srm.services.entity.Users;

public interface TransactionService{
	public List<Users> findAllUsers();
	public Boolean saveOrupdateCategory(Category category);
	public List<Category> getCategory();
	public Boolean deleteCategory(Long Id);
}