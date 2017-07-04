package com.srm.services.rest.dao;

import java.util.List;

import com.srm.services.entity.Category;
import com.srm.services.entity.Users;

public interface TransactionDao {
    public List<Users> findAllUsers();
    public Boolean saveOrupdateCategory(Category category);
    public List<Category> getCategory();
    public Boolean deleteCategory(Category category);
    public Category findCategoryById(Long Id);
}
