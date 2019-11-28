package com.wds.server.wdsserver.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wds.server.wdsserver.domain.QUser;
import com.wds.server.wdsserver.domain.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QUser quser = QUser.user;

    public UserRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<User> getAllUsersOrderByCreatedDate() {
        return jpaQueryFactory.selectFrom(quser).orderBy(quser.create_date.desc()).fetch();
    }

    @Override
    public List<User> lookUpUsers(String filter, String query) {    // TODO filter
        return jpaQueryFactory.selectFrom(quser)
                .where(quser.name.like("%" + query + "%")
                .or(quser.tel.like("%" + query + "%")))
                .orderBy(quser.create_date.desc())
                .fetch();
    }

}
