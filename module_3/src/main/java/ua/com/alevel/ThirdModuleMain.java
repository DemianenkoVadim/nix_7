package ua.com.alevel;

import ua.com.alevel.controller.UserController;
import ua.com.alevel.dto.CategoryType;
import ua.com.alevel.entity.Category;
import ua.com.alevel.factory.ObjectFactory;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

public class ThirdModuleMain {

    public static void main(String[] args) {
        init();
        new UserController().startApplication();
        HibernateUtil.shutdown();
    }

    private static void init() {
        String[] expenseCategory = {"CAR",
                "BUSINESS",
                "CHARITY, HELP, GIFTS",
                "HOUSEHOLD APPLIANCES, COMPUTER, CONSUMABLES",
                "CHILDREN",
                "PETS",
                "HEALTH AND BEAUT",
                "MORTGAGES, DEBTS, LOANS",
                "APARTMENT AND COMMUNICATIONS",
                "TAX AND INSURANCE",
                "EDUCATION",
                "CLOTHING AND ACCESSORIES",
                "RECREATION AND ENTERTAINMENT",
                "MEALS",
                "MISCELLANEOUS",
                "RENOVATION AND FURNITURE",
                "HOUSEHOLD GOODS",
                "TRANSPORT",
                "HOBBY"};
        addCategories(expenseCategory, CategoryType.EXPENSE);
        String[] incomeCategory = {"PRESENTS",
                "SALARY",
                "THE PRIZE",
                "SECOND WORK SALARY",
                "INTEREST ON DEPOSITS",
                "OTHER"
        };
        addCategories(incomeCategory, CategoryType.INCOME);
    }

    private static void addCategories(String[] expenseCategory, CategoryType categoryType) {
        CategoryService categoryService = ObjectFactory.getInstance().getImplClass(CategoryService.class);
        List<Category> current = categoryService.findByType(categoryType);
        Arrays.stream(expenseCategory)
                .filter(c -> current.stream().noneMatch(cat -> cat.getCategory().equals(c)))
                .map(c -> Category.builder()
                        .category(c)
                        .categoryType(categoryType)
                        .build())
                .forEach(categoryService::addCategory);
    }
}
