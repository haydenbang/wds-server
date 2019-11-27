package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.domain.Image;
import com.wds.server.wdsserver.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> getAllUsersOrderByCreatedDate() {
        return imageRepository.getAllImagesOrderByCreatedDate();
    }

    public Image uploadImage(Image image) {
        return imageRepository.save(image);
    }

}
