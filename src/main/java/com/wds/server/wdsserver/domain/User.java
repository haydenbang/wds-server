package com.wds.server.wdsserver.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "_id")
    private String id;

    @Column(name = "pass", nullable = false)
    @NotNull
    @Length(max = 255)
    private String pass ;

    @Column(name = "name", nullable = false)
    @NotNull
    @Length(max = 255)
    private String name  ;

    @Column(name = "nick_name", nullable = false)
    @NotNull
    @Length(max = 255)
    private String nick_name;

    @Column(name = "address", nullable = false)
    @NotNull
    @Length(max = 255)
    private String address;

    @Column(name = "photo", nullable = false)
    @NotNull
    @Length(max = 255)
    private String photo;

    @Column(name = "tel", nullable = false)
    @NotNull
    private String tel;

    @Column(name = "token", nullable = false)
    @NotNull
    private String token;

    @Column(name = "del_yn")
    private String del_yn ;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Column(name = "modify_date")
    private Date modify_date;

    @Column(name = "expired_date")
    private Date expired_date;

    @Column(name = "logout_date")
    private Date logout_date;



}
