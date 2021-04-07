package com.mobius.moa.repository;

import com.mobius.moa.domain.Information;
import com.mobius.moa.domain.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static com.mobius.moa.common.Constants.COLUMN_VALUE_CATEGORIES_NO;

@RequiredArgsConstructor
@Repository
public class ItemsRepository {

    private final EntityManager em;


    public List<Items> find(int i) {
        return em.createQuery("select c from Items c where c.no > :i AND c.no < :i+7", Items.class).setParameter("i", i).getResultList();
    }


    public List<Items> findByCategoriesNo(int categoriesNo) {
        return em.createQuery("SELECT i FROM Items i JOIN i.categories c WHERE c.no = :" + COLUMN_VALUE_CATEGORIES_NO, Items.class).setParameter(COLUMN_VALUE_CATEGORIES_NO, categoriesNo).getResultList();
    }

    public Items findByItemsNo(int itemsNo){
        return em.createQuery("SELECT i FROM Items i WHERE i.no = :itemsNo", Items.class).setParameter( "itemsNo", itemsNo).getSingleResult();
    }
}
