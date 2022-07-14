package com.webPlusJava.demo.testStream;

public class Animal {
    private  final String name;
    private final int age;
    private final  Classificaions classificaions;

    public Animal(String name, int age, Classificaions classificaions) {
        this.name = name;
        this.age = age;
        this.classificaions = classificaions;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classificaions=" + classificaions +
                '}';
    }

    public Classificaions getClassificaions() {
        return classificaions;

    }
}
