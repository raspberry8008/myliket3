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
     * methodName : allCategoryTodoList
     *
     * @return List<?> 할일 목록
     */
//    public List<Tuple> findAll() {
//        return queryFactory
//                .select(
//                        category.categoryName
//                        , todoDetail.todoNo
//                        , todoDetail.todoTitle
//                        , todoDetail.todoContent
//                        , todoDetail.todoDay
//                        , todoDetail.todoTime
//                        , todoDetail.todoState
//                        , grbstate.stateKor

//                )
//                .from(category, todoDetail, grbstate)
//                .where(todoDetail.todoState.eq(grbstate.stateCode))
//                .groupBy(todoDetail.todoNo)
//                .orderBy(todoDetail.todoDay.asc(), todoDetail.todoTime.asc())
//                .fetch();

//    }
}
