package com.myliket.myliket3.domain.entity.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>  {

}

//public interface CategoryRepository extends JpaRepository<Category, UUID>, QuerydslPredicateExecutor {
//}

