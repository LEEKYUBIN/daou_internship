package com.mobius.moa.repository;

import com.mobius.moa.domain.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoriesRepository {

    private final EntityManager em;

    public void save(Categories categories) {
        em.persist(categories);
    }

    public List<Categories> findAll() {
        return em.createQuery("select c from Categories c", Categories.class).getResultList();
    }

    public Categories findByName(String text){
        return em.createQuery("select c from Categories c where c.text = :text", Categories.class).setParameter("text", text).getSingleResult();
    }

}
