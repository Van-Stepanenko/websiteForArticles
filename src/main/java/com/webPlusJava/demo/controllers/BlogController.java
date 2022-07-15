package com.webPlusJava.demo.controllers;

import com.webPlusJava.demo.model.*;
import com.webPlusJava.demo.reposit.LoginPasswordRepository;
import com.webPlusJava.demo.reposit.PostRepository;
import com.webPlusJava.demo.servlet.SetCookiesServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.beans.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//https://www.youtube.com/watch?v=ZJUAR8Xe6CY&ab_channel=%D0%93%D0%BE%D1%88%D0%B0%D0%94%D1%83%D0%B4%D0%B0%D1%80%D1%8C

@Controller
public class BlogController {

    @Autowired // для переменной, которая будет ссылаться на репозиторий
    private PostRepository postRepository; //переменная, которая будет ссылаться на репозиторий
    private Object PSQLException;


    @GetMapping("/blog") //отслеживает ссылку блог, когда пользователь переходит со страницы на страницу
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll(); /*массив данных, в которой будут все значения, полученные из таблички в БД
        имем .findAll() так как наследовали эти функции . сама findAll() вытянит все записи из таблички Post */

        model.addAttribute("posts", posts); // в сам шаблон будем передавать все посты с именем "posts" и значением posts
        return "blog-main"; //возвращает шаблон блог меин.
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }
    //todo ограничение по колиеству символов

    @PostMapping("/blog/add") //отстеживает, когда мы жамкаем на кнопку ( когда срабатываем мтод post)
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model,HttpServletRequest request, HttpServletResponse response) { // @RequestParam для получения новых параметров.
        // название для поля взяли из name="title" в blog-add.html. так же и для anons

        long idUser = -1;
        String nickname = "foo";
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                idUser = Long.parseLong(cookie.getValue()); // присвоим id пользователя( учетки) к статье

                nickname = SQLrequest.getNicknameById(idUser); // присвоим nickname к статье
            }
        } catch (Exception e1) {
            System.out.println("cookie has expired");
        }
        if (idUser > 0) {
            Post post = new Post(title, anons, full_text, idUser, nickname); // создали объект внутри java
            postRepository.save(post); // обращаемся в рпозиторий ( в нашем случае postRepository , вызываем  save для сохранения объекта post
            return "redirect:/blog"; //будет кидать на страничку блог после нажатия на кнопку добавить
        }
        else  return "redirect:/blog/add#user_not_found";

    }




    @GetMapping("/blog/{dynamicID}") // Кнопка (Детальнее)
    //{dynamicID} - ссылка на ПЕРЕМЕННУЮ (динамический параметр), которую мы можем изменять можем написать несколько переменных типа: /blog/{dynamicID}/{pisanina}
    public String blogDetails(@PathVariable(value = "dynamicID") long dynamicID, Model model,HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException { // @PathVariable аннотация позволяет отслеживать динамич. параметр
        Optional<Post> post = postRepository.findById(dynamicID); //на основе класса Optional<из модели Post> создаем объект post и кладем туда -> отслеживаем из рипазитория 1 конкретную запись по id
        //теперь: нам неудобно будет работать с Optional внутри шаблона и сразу его передавать в  model.addAttribute("posts", post);. мы должны перевести его из класса Optional в класс ArrayList
        ArrayList<Post> res = new ArrayList<>();//создали объект res на основе модели Post
        post.ifPresent(res::add);//тут переводит из Optional в ArrayList
        model.addAttribute("post", res); //передаем объект res в наш шаблон

        Post post2 = postRepository.findById(dynamicID).orElseThrow(); // счетчик просмотров статьи ( срабатывает при кнопки Детальнее)
        int viewsNew = post2.getViews();
        viewsNew++;
        post2.setViews(viewsNew);
        postRepository.save(post2);

        long idUser = -1;
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                idUser = Long.parseLong(cookie.getValue()); //читаем id юзера
            }
        }catch (Exception e1){
            System.out.println("cookie has expired");
        }
        long idArticle = Long.parseLong(SQLrequest.getIdUserByIdArticle(dynamicID));

        if ( idUser==idArticle){
            return "blog-details";
        }

        return "blog-details-unlogin";
    }


    @GetMapping("/blog/{dynamicID}/edit") // когда зашли на страницу детальнее
    public String blogDetailsEdit(@PathVariable(value = "dynamicID") long dynamicID, Model model) {
        Optional<Post> post = postRepository.findById(dynamicID);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{dynamicID}/edit") //Кнопка (Обновить статью)
    public String blogPostUpdate(@PathVariable(value = "dynamicID") long dynamicID, @RequestParam String title,
                                 @RequestParam String anons, @RequestParam String full_text, Model model,
                                 HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        Post post = postRepository.findById(dynamicID).orElseThrow();//orElseThrow() вызывает исключение, если функция не найдена
        post.setTitle(title); //  ***.setTitle беется из Post.java как сеттер. ***(title) - принимаем чуть выше в ( @RequestParam String title)
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post); //обновляем уже существующуб запись
        return "redirect:/blog";
    }

    @PostMapping("/blog/{dynamicID}/edit/nameUpdate")// Кнопка обнова никнейма
    public String blogPostUpdateUsername(@PathVariable(value = "dynamicID") long dynamicID, Model model, HttpServletRequest request) {
        long idUser = -1;
        String nickname = "foo";
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                idUser = Long.parseLong(cookie.getValue());

                nickname = SQLrequest.getNicknameById(idUser); // присвоим nickname к статье
            }
        }catch (Exception e1){
            System.out.println("cookie has expired");
        }
        Post post = postRepository.findById(dynamicID).orElseThrow();//orElseThrow() вызывает исключение, если функция не найдена
        post.setNickname(nickname);
        postRepository.save(post);
        return "redirect:/blog";
    }


    @PostMapping("/blog/{dynamicID}/remove")// Кнопка (Удалить)
    public String blogPostDelete(@PathVariable(value = "dynamicID") long dynamicID, Model model) {
        Post post = postRepository.findById(dynamicID).orElseThrow();//orElseThrow() вызывает исключение, если функция не найдена
        postRepository.delete(post);
        return "redirect:/blog";
    }

    @Autowired // для переменной, которая будет ссылаться на репозиторий
    private LoginPasswordRepository loginPasswordRepository ; //переменная, которая будет ссылаться на репозиторий

    @GetMapping("/authorization")
    public String authorization (Model model,  HttpServletRequest request, HttpServletResponse response) {


        String nameCookies = "";
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                nameCookies = cookie.getName(); //нашли имя куки( логин пользователя)
            }

        }catch (Exception e1){
            System.out.println("cookie has expired"); // если cookies авторизации нет, идем в авторизацию
            return "authorization";
        }


        return "/deAuthorization";
    }



    @PostMapping("/authorization")
    public String authorization(@RequestParam String login, @RequestParam String password, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {

       LoginPassword loginPassword = new LoginPassword(login, password);
        Iterable<LoginPassword> allLoginPasswords = loginPasswordRepository.findAll();
        String loginUser = loginPassword.getLogin();
        String passwordUser = loginPassword.getPassword();
        Long logIsCorrect = Authorization.loginFound(loginUser,passwordUser);//  отправляем на проверку логин и пароль/ получаем id авториз пользователя
        String idForCookie = String.valueOf(logIsCorrect);
        if (logIsCorrect == -1){ // логин не найденн
            return "redirect:/authorization#user_not_found";
        }
        else if ( logIsCorrect == -2){ // логин и пароль не совпал
            return "redirect:/authorization#password_not_correct";
        }
        else if ( logIsCorrect > -1){ //совпал логин и пароль
            Cookie cookLoginId = new Cookie(loginUser,idForCookie);
            cookLoginId.setMaxAge(60*60); //время жизни куки 60сек*60 = 1 час
            response.addCookie(cookLoginId);

            //https://www.youtube.com/watch?v=Jnd4PQt44j0&ab_channel=letsCode
            return "redirect:/authorization#correct";
        }
        return "/home";
    }



    @GetMapping("/authorization/redact-user")
    public String redactUserInf (Model model) {
        return "redact-user";
    }


    @PostMapping("/authorization/redact-user")
    public String redactUserInfo( @RequestParam String password, @RequestParam String nickname,
                                 HttpServletRequest request, HttpServletResponse response,  Model model) throws SQLException, ClassNotFoundException {

        long idUser = -1;
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                idUser = Long.parseLong(cookie.getValue()); //

            }
        } catch (Exception e1) {
            System.out.println("cookie has expired" + idUser);
        }

        boolean nicknameIsCorrect = NicknameIsCorrect.loginFound(idUser, nickname);
        System.out.println(nicknameIsCorrect);

        if (nicknameIsCorrect) {
            LoginPassword loginPassword = loginPasswordRepository.findById(idUser).orElseThrow();
            loginPassword.setPassword(password);
            loginPassword.setNickname(nickname);

            loginPasswordRepository.save(loginPassword);

            return "/home";
        }
        else return "redirect:/authorization/redact-user#nicknameIncorrect";
    }


    @GetMapping("/deAuthorization")
    public String deAuthorization (Model model) {
        return "deAuthorization";
    }

    @PostMapping ("/deAuthorization")
    public String deAuthorizationCorrect1 (Model model,HttpServletRequest request, HttpServletResponse response) {

        String nameCookies = "";
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                nameCookies = cookie.getName(); //нашли имя куки( логин пользователя)
            }

        Cookie cookie1 = new Cookie(nameCookies,"");
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        }catch (Exception e1){
            System.out.println("cookie has expired");
            return "redirect:/deAuthorization#user_not_found";
        }


        return "redirect:/deAuthorization#deAythorization";
    }


    @GetMapping("/newUser")
    public String newUser (Model model) {
        return "newUser";
    }

    @PostMapping("/newUser")
    public String newUser(@RequestParam String login, @RequestParam String password, @RequestParam String nickname,  Model model) {
        try {
            LoginPassword loginPassword = new LoginPassword(login, password, nickname);

            Iterable<LoginPassword> loginPasswords = loginPasswordRepository.findAll(); // todo скорее всего не нужно
            String passIsNotNull = loginPassword.getPassword();
            //String nicknameCorrect = loginPassword.getNickname();
            if (passIsNotNull == ""){
                return "redirect:/newUser#passIsNotCorrect";
            }
            else {
            ArrayList<LoginPassword> res = new ArrayList<>();
            model.addAttribute("post", res);

            loginPasswordRepository.save(loginPassword);
            return "redirect:/newUser#LogIsOk";
            }
        }
        catch (Exception e1){

            return "redirect:/newUser#newUserError";
        }
    }
    }