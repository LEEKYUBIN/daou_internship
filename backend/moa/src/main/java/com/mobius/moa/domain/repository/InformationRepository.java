package com.mobius.moa.domain.repository;

import com.mobius.moa.domain.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Integer> {
}
