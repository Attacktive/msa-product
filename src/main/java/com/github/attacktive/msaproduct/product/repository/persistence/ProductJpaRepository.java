package com.github.attacktive.msaproduct.product.repository.persistence;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
	Page<ProductEntity> findAllByIdIn(Set<Long> ids, PageRequest pageRequest);
}
