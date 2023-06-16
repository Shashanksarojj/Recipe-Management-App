package service;

import java.util.List;

import entity.Category;
import exception.ServiceException;

public interface CategoryService {
	
    void addCategory(Category category) throws ServiceException;
    void updateCategory(Category category) throws ServiceException;
    void deleteCategory(int categoryId) throws ServiceException;
    Category getCategoryById(int categoryId) throws ServiceException;
    List<Category> getAllCategories() throws ServiceException;
}
