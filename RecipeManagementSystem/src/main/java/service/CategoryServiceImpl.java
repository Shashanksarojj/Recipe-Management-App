package service;

import java.util.ArrayList;
import java.util.List;

import entity.Category;
import exception.ServiceException;

public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories;

    public CategoryServiceImpl() {
        this.categories = new ArrayList<>();
    }

    @Override
    public void addCategory(Category category) throws ServiceException {
        categories.add(category);
    }

    @Override
    public void updateCategory(Category category) throws ServiceException {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == category.getId()) {
                categories.set(i, category);
                return;
            }
        }
        throw new ServiceException("Category not found.");
    }

    @Override
    public void deleteCategory(int categoryId) throws ServiceException {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                categories.remove(category);
                return;
            }
        }
        throw new ServiceException("Category not found.");
    }

    @Override
    public Category getCategoryById(int categoryId) throws ServiceException {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        throw new ServiceException("Category not found.");
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        return categories;
    }
}