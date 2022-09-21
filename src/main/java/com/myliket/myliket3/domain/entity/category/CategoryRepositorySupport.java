package com.myliket.myliket3.domain.entity.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class CategoryRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CategoryRepositorySupport(JPAQueryFactory queryFactory) {
        super(Category.class);
        this.queryFactory = queryFactory;
    }

//    /**
//     * 카테고리 이름 검색
//     * methodName : getCategoryByCategoryId
//     * @param categoryName 카테고리 이름
//     * @return Category(Object)
//     * */
//
//    public List<Category> findByName(String categoryName) {
//        return queryFactory
//                .selectFrom(category)
//                .where((category.categoryName.eq(categoryName)))
//                .fetch();
//    }
}
