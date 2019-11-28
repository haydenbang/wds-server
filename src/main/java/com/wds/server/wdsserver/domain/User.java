package com.wds.server.wdsserver.domain;

import com.wds.server.wdsserver.enums.Authority;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiParam(hidden = true)
    private Long idx;

    @Column(name = "_id")
    private String id;

    @Column(name = "pass", nullable = false)
    @NotNull
    @Length(max = 255)
    private String pass;

    @Column(name = "name", nullable = false)
    @NotNull
    @Length(max = 255)
    private String name;

    @Column(name = "nick_name", nullable = false)
    @NotNull
    @Length(max = 255)
    private String nick_name;

    @Column(name = "address", nullable = false)
    @NotNull
    @Length(max = 255)
    private String address;

    @Column(name = "photo", columnDefinition = "mediumblob")
    @Lob
    private Byte[] photo;

    @Column(name = "tel", nullable = false)
    @NotNull
    private String tel;

    @Column(name = "authority", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "token")
    @ApiParam(hidden = true)
    private String token;

    @Column(name = "del_yn")
    private String del_yn ;

    @Column(name = "create_date")
    @CreationTimestamp
    @ApiParam(hidden = true)
    private Date create_date;

    @Column(name = "modify_date")
    @UpdateTimestamp
    @ApiParam(hidden = true)
    private Date modify_date;

    @Column(name = "expired_date")
    @ApiParam(hidden = true)
    private Date expired_date;

    @Column(name = "logout_date")
    @ApiParam(hidden = true)
    private Date logout_date;



}
