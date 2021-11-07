package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.dto.CategoryType;
import ua.com.alevel.entity.Category;
import ua.com.alevel.factory.ObjectFactory;
import ua.com.alevel.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl() {
        this(ObjectFactory.getInstance().getImplClass(CategoryDao.class));
    }

    @Override
    public void addCategory(Category category)  {
        categoryDao.addCategory(category);
    }

    @SneakyThrows
    @Override
    public List<Category> findByType(CategoryType categoryType) {
        return  categoryDao.findAll().stream()
                .filter(c -> c.getCategoryType() == CategoryType.INCOME || c.getCategoryType() == CategoryType.EXPENSE)
                .toList();
    }

    @Override
    public void printsAllIncomeCategory() throws SQLException {
        categoryDao.findAll().stream()
                .filter(c -> c.getCategoryType() == CategoryType.INCOME)
                .forEach(c -> System.out.printf("%d - %s%n", c.getCategoryId(), c.getCategory()));
    }

    @Override
    public void printsAllExpenseCategory() throws SQLException {
        categoryDao.findAll().stream()
                .filter(c -> c.getCategoryType() == CategoryType.EXPENSE)
                .forEach(c -> System.out.printf("%d - %s%n", c.getCategoryId(), c.getCategory()));
    }
}
