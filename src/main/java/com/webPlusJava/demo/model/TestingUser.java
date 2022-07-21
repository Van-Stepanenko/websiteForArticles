package com.webPlusJava.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collections;
import java.util.List;

@Entity //показывает, что это модель отвечает за определенную таблицу в БД bean
public class TestingUser {

    @Id //именно id из javax.persistence
    @GeneratedValue(strategy = GenerationType.AUTO) //генерирует каждый раз новое значение внутри поля
    private Long id; //обычно id делают Long
    private String questions;
    private String responseFromQuestions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getResponseFromQuestions() {
        return responseFromQuestions;
    }

    public void setResponseFromQuestions(String responseFromQuestions) {
        this.responseFromQuestions = responseFromQuestions;
    }

    public TestingUser(){
    }

    public TestingUser(Long id, String questions, String responseFromQuestions) {
        this.id = id;
        this.questions = questions;
        this.responseFromQuestions = responseFromQuestions;
    }
}
