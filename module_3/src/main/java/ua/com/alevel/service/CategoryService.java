package ua.com.alevel.service;

import ua.com.alevel.dto.CategoryType;
import ua.com.alevel.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    void addCategory(Category category) ;
    List<Category> findByType(CategoryType categoryType) ;

    void printsAllIncomeCategory() throws SQLException;

    void printsAllExpenseCategory() throws SQLException;
}
