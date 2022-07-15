package com.webPlusJava.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Entity //показывает, что это модель отвечает за определенную таблицу в БД bean
public class Post { // создаем таблицу с разными свойствами


    @Id //именно id из javax.persistence
    @GeneratedValue(strategy = GenerationType.AUTO) //генерирует каждый раз новое значение внутри поля
    private Long id; //обычно id делают Long

    public Long idUser;
    public  int likeBlog;

    private String title, anons, full_text, nickname;


    private int views;

    public int getLikeBlog() {
        return likeBlog;
    }

    public void setLikeBlog(int likeBlog) {
        this.likeBlog = likeBlog;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Post() { // обязательно должен присутствовать пустой конструктор! для корректной работы. В любой моделе тоакое толжно быть
    }

    public Post(String title, String anons, String full_text, long idUser, String nickname, int likeBlog) { // служит чтоб передать в BlogController.java данные на основе анонса  @PostMapping
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.idUser = idUser;
        this.nickname = nickname;

    }
    public Post(int likeBlog){
        this.likeBlog = likeBlog;
    }
   /* public Post(Long idLike){
        this.idLike = Collections.singletonList(idLike);
    } */


}
