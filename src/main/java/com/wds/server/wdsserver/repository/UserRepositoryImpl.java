package com.wds.server.wdsserver.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wds.server.wdsserver.domain.QUser;
import com.wds.server.wdsserver.domain.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    private JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());;
    private QUser quser = QUser.user;

    public UserRepositoryImpl() { super(User.class);}

    @Override
    public List<User> getAllUsersOrderByCreatedDate() {
        return queryFactory.selectFrom(quser).orderBy(quser.create_date.desc()).fetch();
    }

    @Override
    public List<User> lookUpUsers(String filter, String query) {    // TODO filter
        return queryFactory.selectFrom(quser)
                .where(quser.name.like(query).or(quser.tel.like(query)))
                .fetch();
    }

}
