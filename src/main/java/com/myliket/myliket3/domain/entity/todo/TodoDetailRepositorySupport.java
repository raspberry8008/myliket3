package com.myliket.myliket3.domain.entity.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDetailRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public TodoDetailRepositorySupport(JPAQueryFactory queryFactory) {
        super(TodoDetail.class);
        this.queryFactory = queryFactory;
    }

    /**
     * 전체 할일을 조회 한다.
     * methodName : findAll
     *
     * @return List<?> 할일 목록
     */
//    public List<TodoDetail> findAll() {
//        return queryFactory
//                .select(
//                        category.categoryName
//                        , todoDetail.todoNo
//                        , todoDetail.todoTitle
//                        , todoDetail.todoContent
//                        , todoDetail.todoDay
//                        , todoDetail.todoTime
//                        , todoState.todoStateCode
//
//                )
//                .from(category, todoDetail, todoState)
//                .where(todoDetail.todoState.eq(todoState.todoStateCode))
//                .groupBy(todoDetail.todoNo)
//                .orderBy(todoDetail.todoDay.asc(), todoDetail.todoTime.asc())
//                .fetch();
//
//    }
}
