package com.wds.server.wdsserver.repository;

import com.wds.server.wdsserver.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ImageRepository extends JpaRepository<Image, Long>, QuerydslPredicateExecutor<Image>,
        ImageRepositoryCustom {
}
