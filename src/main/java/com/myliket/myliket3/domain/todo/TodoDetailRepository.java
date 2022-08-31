package com.myliket.myliket3.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoDetailRepository extends JpaRepository<TodoDetail, Long> {

    List<TodoDetail> findByCategoryId (UUID categoryId);
}
