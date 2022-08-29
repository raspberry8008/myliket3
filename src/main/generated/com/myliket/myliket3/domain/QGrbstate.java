package com.myliket.myliket3.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGrbstate is a Querydsl query type for Grbstate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGrbstate extends EntityPathBase<Grbstate> {

    private static final long serialVersionUID = 1225822082L;

    public static final QGrbstate grbstate = new QGrbstate("grbstate");

    public final StringPath stateCategory = createString("stateCategory");

    public final StringPath stateCode = createString("stateCode");

    public final StringPath stateKor = createString("stateKor");

    public QGrbstate(String variable) {
        super(Grbstate.class, forVariable(variable));
    }

    public QGrbstate(Path<? extends Grbstate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGrbstate(PathMetadata metadata) {
        super(Grbstate.class, metadata);
    }

}

