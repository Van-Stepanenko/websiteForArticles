package com.webPlusJava.demo.reposit;

import com.webPlusJava.demo.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post, Long>{ // наследует все от интерфейса CrudRepository(встроенный репоситорий) Long - тип как у id

}
