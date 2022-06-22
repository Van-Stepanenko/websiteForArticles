package com.webPlusJava.demo.reposit;

import com.webPlusJava.demo.model.LoginPassword;
import org.springframework.data.repository.CrudRepository;

public interface LoginPasswordRepository extends CrudRepository <LoginPassword, Long>{ // наследует все от интерфейса CrudRepository(встроенный репоситорий) Long - тип как у id

}
