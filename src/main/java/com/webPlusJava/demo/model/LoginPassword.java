package com.webPlusJava.demo.model;

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;

@Entity //показывает, что это модель отвечает за определенную таблицу в БД bean
public class LoginPassword { // создаем таблицу с разными свойствами


    @Id //именно id из javax.persistence
    @GeneratedValue(strategy = GenerationType.AUTO) //генерирует каждый раз новое значение внутри поля
    private Long id; //обычно id делают Long

    private String login, password, nickname;

    private int views;

    public String idBlogLike;

    public String getIdBlogLike() {
        return idBlogLike;
    }

    public void setIdBlogLike(String idBlogLike) {
        this.idBlogLike = idBlogLike;
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

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public LoginPassword() { // обязательно должен присутствовать пустой конструктор! для корректной работы. В любой моделе тоакое толжно быть
    }

    public LoginPassword(String login, String password, String nickname) { // служит чтоб передать в BlogController.java данные на основе анонса  @PostMapping
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }
    public LoginPassword(String login, String password) { // служит чтоб передать в BlogController.java данные на основе анонса  @PostMapping
        this.login = login;
        this.password = password;
    }
    public LoginPassword(Long id){
        this.id=id;
    }

    public LoginPassword(String idBlogLike){
        this.idBlogLike = idBlogLike;
    }
}
