package com.myliket.myliket3.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>  {
}

//public interface CategoryRepository extends JpaRepository<Category, UUID>, QuerydslPredicateExecutor {
//}

