package com.wds.server.wdsserver.repository;

import com.wds.server.wdsserver.domain.Image;

import java.util.List;

public interface ImageRepositoryCustom {
    List<Image> getAllImagesOrderByCreatedDate();
}
