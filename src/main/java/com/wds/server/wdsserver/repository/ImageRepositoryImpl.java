package com.wds.server.wdsserver.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wds.server.wdsserver.domain.Image;
import com.wds.server.wdsserver.domain.QImage;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl extends QuerydslRepositorySupport implements ImageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QImage qimage = QImage.image;

    public ImageRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Image.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Image> getAllImagesOrderByCreatedDate() {
        return jpaQueryFactory.selectFrom(qimage).orderBy(qimage.create_date.desc()).fetch();
    }

}
