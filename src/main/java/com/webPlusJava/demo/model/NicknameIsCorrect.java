package com.webPlusJava.demo.model;

import java.sql.SQLException;


public class NicknameIsCorrect {

    String pass; // переопределили для верного сравнивания

    public boolean equals(Authorization password) {
        return this.pass == password.pass;
    }


    public static boolean loginFound(long idUser, String newNickname) throws ClassNotFoundException, SQLException {


        String oldNickname = SQLrequest.getNicknameById(idUser); // нашли текущий никнейм
        String loginByNewNickname = SQLrequest.getLoginByNickname(newNickname); // нашли логин нового ника
        String loginByOldNickname = SQLrequest.getLoginByNickname(oldNickname);// нашли логин старого ника
        if (loginByNewNickname.equals(loginByOldNickname)) {// если лог старого и лог нового ника совпадают
            return true;
        } else if (loginByNewNickname.equals("")) { // если НЕ нашли никакого логина к новому нику
            return true;
        } else return false; //если логин нового никнейма Не пренадлежит текущему юзеру

    }
}