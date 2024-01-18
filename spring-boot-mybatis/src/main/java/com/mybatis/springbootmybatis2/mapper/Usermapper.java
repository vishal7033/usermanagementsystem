package com.mybatis.springbootmybatis2.mapper;

import com.mybatis.springbootmybatis2.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Usermapper {
    List<User> getAll();

    User getUserById(Integer id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);

}
