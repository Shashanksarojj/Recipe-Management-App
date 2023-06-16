package dao;

import java.util.List;

import entity.Category;

public interface CategoryDAO {


    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);

    Category getCategoryById(int categoryId);

    List<Category> getAllCategories();
}
