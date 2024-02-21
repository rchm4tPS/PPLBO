package com.blog.vo;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    // generator should be set to native, so the same for generic generator
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "user")
    private String user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "updt_date")
    private Date updtDate;

    public Post() {
    }

    public Post(Long id, String title, String content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(Long id, String user, String title, String content) {
        super();
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.regDate = new Date();
        this.updtDate = new Date();
    }

    public Post(String user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.regDate = new Date();
        this.updtDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdtDate() {
        return updtDate;
    }

    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }

}
