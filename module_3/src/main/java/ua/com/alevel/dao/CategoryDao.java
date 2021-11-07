package ua.com.alevel.dao;

import ua.com.alevel.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    void addCategory(Category category) ;

    Category findById(Long id) throws SQLException;

    List<Category> findAll() throws SQLException;
}
