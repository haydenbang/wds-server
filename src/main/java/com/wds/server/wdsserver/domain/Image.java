package com.wds.server.wdsserver.domain;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @Column(name = "image_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiParam(hidden = true)
    private Long imageNo;

    @Column(name = "user_idx")
    @NotNull
    private Long userIdx;

    @Column(name = "user_name")
    @NotNull
    @Length(max = 255)
    private String userName;

    @Column(name = "file_name")
    @Length(max = 255)
    @ApiParam(defaultValue = "image")
    private String fileName;

    @Column(name = "photo", columnDefinition = "mediumblob")
    @NotNull
    @Lob
    private Byte[] photo;

    @Column(columnDefinition = "text", name = "description")
    private String description;

    @Column(name = "del_yn")
    @ApiParam(hidden = true)
    private String del_yn ;

    @Column(name = "create_date")
    @CreationTimestamp
    @ApiParam(hidden = true)
    private Date create_date;

    @Column(name = "modify_date")
    @UpdateTimestamp
    @ApiParam(hidden = true)
    private Date modify_date;

}
