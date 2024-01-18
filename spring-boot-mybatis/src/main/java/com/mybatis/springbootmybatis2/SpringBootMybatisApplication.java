package com.mybatis.springbootmybatis2;

import com.mybatis.springbootmybatis2.model.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MappedTypes(User.class)
@MapperScan("com.mybatis.springbootmybatis2.mapper")
public class SpringBootMybatisApplication {

	public static void main(String[] args)

	{
		SpringApplication.run(SpringBootMybatisApplication.class, args);
	}
}
