package com.myliket.myliket3.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodoDetail is a Querydsl query type for TodoDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoDetail extends EntityPathBase<TodoDetail> {

    private static final long serialVersionUID = -1668197473L;

    public static final QTodoDetail todoDetail = new QTodoDetail("todoDetail");

    public final ComparablePath<java.util.UUID> categoryId = createComparable("categoryId", java.util.UUID.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath todoContent = createString("todoContent");

    public final DatePath<java.time.LocalDate> todoDay = createDate("todoDay", java.time.LocalDate.class);

    public final NumberPath<Long> todoNo = createNumber("todoNo", Long.class);

    public final StringPath todoState = createString("todoState");

    public final TimePath<java.time.LocalTime> todoTime = createTime("todoTime", java.time.LocalTime.class);

    public final StringPath todoTitle = createString("todoTitle");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QTodoDetail(String variable) {
        super(TodoDetail.class, forVariable(variable));
    }

    public QTodoDetail(Path<? extends TodoDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodoDetail(PathMetadata metadata) {
        super(TodoDetail.class, metadata);
    }

}

