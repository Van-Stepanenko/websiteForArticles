package com.webPlusJava.demo.testStream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        List<Animal> animals = getAnimals(); //ссыль на лист

        //Старый подход(Императивный):

        List<Animal> predators = new ArrayList<>();//создали новы лист хищники
        for (Animal animal : animals) { // цикл проверки каждого животного
            if (animal.getClassificaions().equals(Classificaions.PREDATOR)){
                predators.add(animal);
            }
        }
        predators.forEach(System.out::println);

        //Новый подход (Декларативный)

        //Filter
        List<Animal> predator = animals.stream() //создали стрим
                .filter(animal -> animal.getClassificaions().equals(Classificaions.PREDATOR)) //фильтр принимает на вход предикат
                //предикат возвращает либо тру либо фалс. Если тру, то делам :
                .collect(Collectors.toList()); //положи в коллекцию

        predator.forEach(System.out::println);

        //Sort сортировка
        List<Animal> sorted = animals.stream()
                .sorted(Comparator.comparing(Animal::getAge)
                        .thenComparing(Animal::getClassificaions) // после сортировки по возрасту сортируем по классификации животных
                        .reversed()
                        .reversed()) //reversed делает реверс еще раз

                //. еще какую нибудь сортировку можем добавить
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        //All match возвращает boolean . Говорит о том, что все элементы листа выполняют каким либо условиям

        boolean allMatch = animals.stream()
                .allMatch(animal -> animal.getAge() > 10); //проверяем, что все больше 10
        System.out.println(allMatch);

        //Any match если хотя бы для 1 элемента условие выполнено, вернет true
        boolean anyMatch = animals.stream()
                .anyMatch(animal -> animal.getClassificaions().equals(Classificaions.PREDATOR)); // выдаст true если
        //хотяб 1 элемент является predator
        System.out.println(anyMatch);
        //None match если не удоволетворяет условие, вернет true ( если нет Fox , вернет true)
        boolean noneMatch = animals.stream()
                .noneMatch(animal -> animal.getName().equals("Fox"));
        System.out.println(noneMatch);
        //Max / этот метод возвращает optional
        animals.stream()
                .max(Comparator.comparing(Animal::getAge))
                .ifPresent((System.out::println));
        //Min
        animals.stream()
                .min(Comparator.comparing(Animal::getAge))
                .ifPresent((System.out::println));
        //Group
        Map<Classificaions, List<Animal>> classificationListMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getClassificaions));
        classificationListMap.forEach((((classificaions, animals1) -> {
            System.out.println(classificaions);
            animals1.forEach(System.out::println);
            System.out.println();
        })));

        //все методы можно объединять. Возьмем самого старого хищника:

        Optional<String> oldPredator = animals.stream()
                .filter(animal -> animal.getClassificaions().equals(Classificaions.PREDATOR))
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getName);
        oldPredator.ifPresent(System.out::println);

        List<Animal> predator1 = animals.stream() //создали стрим
                .filter(animal -> animal.getClassificaions().equals(Classificaions.PREDATOR)) //фильтр принимает на вход предикат
                .sorted(Comparator.comparing(Animal::getAge))
                //предикат возвращает либо тру либо фалс. Если тру, то делам :
                .collect(Collectors.toList()); //положи в коллекцию

        predator1.forEach(System.out::println);




    }
    private static List<Animal> getAnimals(){
        return List.of(
                new Animal("Cat", 3, Classificaions.PREDATOR),
                new Animal("Fog", 11, Classificaions.PREDATOR),
                new Animal("Dino", 333, Classificaions.PREDATOR),
                new Animal("Hour", 9, Classificaions.HERBIVORE),
                new Animal("Gibbon", 14, Classificaions.OMNIVOROUS),
                new Animal("Maus", 1, Classificaions.OMNIVOROUS),
                new Animal("Bison", 3, Classificaions.HERBIVORE),
                new Animal("Alligator", 31, Classificaions.PREDATOR)

                );
    }
}
