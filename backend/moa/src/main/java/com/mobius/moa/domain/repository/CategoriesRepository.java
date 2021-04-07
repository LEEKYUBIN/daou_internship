package com.mobius.moa.domain.repository;

import com.mobius.moa.domain.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
