package com.atguigu;

import com.atguigu.entity.User;
import com.atguigu.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTestDemo {
    @Autowired
    private UserService userService;

    @Test
    public void userServiceTest01(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName,"test");
        List<User> list = userService.list(wrapper);
        list.forEach(System.out::println);
    }

}
