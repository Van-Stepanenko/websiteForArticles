package com.webPlusJava.demo.model;


import java.sql.SQLException;

public class Authorization {

    String pass; // переопределили для верного сравнивания
    public boolean equals(Authorization password){
        return this.pass == password.pass;
    }


    public static Long loginFound (String logUser, String passUser) throws ClassNotFoundException, SQLException {

        Long foundId = Long.valueOf(0);
        foundId = SQLrequest.getIdByLogin(logUser); //находим id по , значение которого logUser
        String foundPass;
        foundPass = SQLrequest.getPasswordById(foundId);
        if (foundId>0){
            if (foundPass.equals(passUser)){
                return foundId;//совпал логин и пароль
            }
            else return Long.valueOf(-2);//логин и пароль не совпал
        }
        else  return Long.valueOf(-1);// лонин не найден
    }
}
