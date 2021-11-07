package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.entity.Category;
import ua.com.alevel.util.HibernateUtil;

import java.util.List;

import static ua.com.alevel.util.SessionsUtil.saveInputInformationSession;

@Log4j2
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void addCategory(Category category) {
        saveInputInformationSession(category);
    }

    @Override
    public Category findById(Long id) {
        Category category = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var categoryQuery = session.createQuery("FROM Category c where c.id =: id", Category.class);
            category = categoryQuery.setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            log.info("Can not find category with this id ");
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Category ", Category.class)
                    .list();
        } catch (Exception e) {
            log.info("Cannot find all categories");
        }
        return List.of();
    }
}
