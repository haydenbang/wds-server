package com.wds.server.wdsserver.service;

import com.wds.server.wdsserver.domain.Image;
import com.wds.server.wdsserver.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> getAllImagesOrderByCreatedDate() {
        return imageRepository.getAllImagesOrderByCreatedDate();
    }

    public Image uploadImage(Image image) {
        return imageRepository.save(image);
    }

    public Image getImage(Long imageNo) throws Exception {
        Optional<Image> optionalImage = imageRepository.findById(imageNo);
        Image image = new Image();
        
        if(optionalImage.isPresent()){
            image = optionalImage.get();
        }else{
            log.info("존재하지 않는 이미지입니다.");
            throw new Exception();  // TODO 400 Error
        }
        
        return image;
    }

    public Image updateImage(Image imageDetail) {
        Optional<Image> optionalImage = imageRepository.findById(imageDetail.getImageNo());

        Image image = new Image();
        if(optionalImage.isPresent()){
            image = optionalImage.get();
        }
        image.setPhoto(imageDetail.getPhoto());

        return imageRepository.save(image);
    }

    public void deleteImage(Long imageNo) throws Exception {
        try{
            Optional<Image> optionalImage = imageRepository.findById(imageNo);
            if(optionalImage.isPresent()){
                Image image  = optionalImage.get();
                imageRepository.delete(image);
            }else{
                log.info("존재하지 않는 이미지입니다.");
                throw new Exception();  // TODO 400 Error
            }
        }catch (Exception e){
            log.info("Error");
            throw new Exception();
        }
    }
}
