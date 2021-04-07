package com.mobius.moa.repository;

import com.mobius.moa.domain.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class InformationRepository {

    private final EntityManager em;

    public List<Information> findByAttributeText(String text){
        return em.createQuery("SELECT i FROM Information i where i.attributeText = :text", Information.class).setParameter("text", text).getResultList();
    }
}