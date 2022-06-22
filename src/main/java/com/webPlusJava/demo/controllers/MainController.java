package com.webPlusJava.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//https://www.youtube.com/watch?v=5s50Zj8YIE8&ab_channel=%D0%93%D0%BE%D1%88%D0%B0%D0%94%D1%83%D0%B4%D0%B0%D1%80%D1%8C

@Controller // контроллер отвечает за обработку всех переходов на сайт
public class MainController {

    @GetMapping("/") //указывает на часть сайта, где будет применятся код ниже. в данном случае (/) - это главная страница
    public String home( Model model) { // model - обязательный параметр, котоырй всегда принемается
        model.addAttribute("title", "Главная страница");
        return "home"; // при открытии главной страницы будет вызываться шаблон home
    }
    @GetMapping("/about") //указывает на часть сайта, где будет применятся код ниже. в данном случае (/about) - это еще одна страничка
    public String about( Model model) { // model - обязательный параметр, котоырй всегда принемается
        model.addAttribute("title", "Страница про нас");
        return "about"; // при открытии главной страницы будет вызываться шаблон about
    }
    @GetMapping("/blog-main") //указывает на часть сайта, где будет применятся код ниже. в данном случае (/about) - это еще одна страничка
    public String blogMain( Model model) { // model - обязательный параметр, котоырй всегда принемается
        model.addAttribute("title", "<Блог>");
        return "blog-main"; // при открытии главной страницы будет вызываться шаблон about
    }

}