package com.wds.server.wdsserver.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wds.server.wdsserver.domain.QUser;
import com.wds.server.wdsserver.domain.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() { super(User.class);}
    private QUser quser = QUser.user;

    @Override
    public List<User> getAllUsersOrderByCreatedDate() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());
        return queryFactory.selectFrom(quser).orderBy(quser.create_date.desc()).fetch();
    }
}
