package com.mobius.moa.domain.repository;

import com.mobius.moa.domain.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemsRepository extends JpaRepository<Items, Integer>, ItemsRepositoryCustom {
}
