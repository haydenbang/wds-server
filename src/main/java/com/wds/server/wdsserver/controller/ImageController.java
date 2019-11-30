package com.wds.server.wdsserver.controller;

import com.wds.server.wdsserver.commons.ResponseType;
import com.wds.server.wdsserver.domain.Image;
import com.wds.server.wdsserver.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@Api(value = "Image", tags = "Image")
public class ImageController {

    private final ImageService imageService;
    private final ResponseType responseType;

    @GetMapping
    @ApiOperation(value = "전체 이미지 리스트 조회", nickname = "전체 이미지 리스트 조회", notes = "전체 이미지 정보를 가져온다.")
    public ResponseEntity<?> getAllImages() {
        return responseType.send(imageService.getAllImagesOrderByCreatedDate());
    }

    @PostMapping
    @ApiOperation(value = "이미지 업로드", nickname = "이미지 업로드", notes = "이미지를 업로드 한다.")
    public ResponseEntity<?> uploadImage(@Valid @ModelAttribute Image image) {
        return responseType.send(imageService.uploadImage(image));
    }

    @GetMapping("/{image_no}")
    @ApiOperation(value="이미지 조회", nickname = "이미지 조회", notes ="이미지 정보를 가져온다.")
    public ResponseEntity<?> getImage(@PathVariable("image_no") Long imageNo) throws Exception{
        return responseType.send(imageService.getImage(imageNo));
    }

    @PutMapping("/{image_no}")
    @ApiOperation(value="이미지 정보 수정", nickname = "이미지 정보 수정", notes="이미지 정보를 수정한다.")
    public ResponseEntity<?> updateImage(@PathVariable("image_no") Long imageNo,
                                         @Valid @ModelAttribute Image imageDetail){
        return responseType.send(imageService.updateImage(imageDetail));
    }

    @DeleteMapping("/{image_no}")
    @ApiOperation(value="이미지 삭제", nickname = "이미지 삭제", notes="이미지를 삭제한다.")
    public ResponseEntity<?> deleteImage(@PathVariable("image_no") Long imageNo) throws Exception{
        imageService.deleteImage(imageNo);
        return responseType.send();
    }

}
