package com.wds.server.wdsserver.repository;

import com.wds.server.wdsserver.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>, QuerydslPredicateExecutor<Image>,
        ImageRepositoryCustom {
}
