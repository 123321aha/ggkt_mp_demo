package com.atguigu;

import com.atguigu.entity.User;
import com.atguigu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class QueryTestsDemo {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryTest01(){
        //创建条件构造对象
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        QueryWrapper<User> wrapper3 = new QueryWrapper<>();
        //ge、gt、le、lt
        //以ge为例，有两个参数，第一个参数为表中的字段名，第二个参数为参数值
        wrapper1.ge("age",30);
        wrapper2.eq("name","lalala");
        wrapper3.like("name","test").orderByDesc("id");
        //调用查询方法实现条件查询
        List<User> userList1 = userMapper.selectList(wrapper1);
        List<User> userList2 = userMapper.selectList(wrapper2);
        List<User> userList3 = userMapper.selectList(wrapper3);
//        System.out.println(userList.size());
    }


    @Test
    public void queryTest02(){
        //创建条件构造对象
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        //ge、gt、le、lt
        //以ge为例，有两个参数，第一个参数为表中的字段名，第二个参数为参数值
        wrapper1
                .like("name","test")
                .ge("age",30);
        //调用查询方法实现条件查询
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper1);
        List<User> userList = userMapper.selectList(wrapper1);
        maps.forEach(System.out::println);
        userList.forEach(System.out::println);
    }

    @Test
    public void queryTest03(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(User::getName,"Test")
                .ge(User::getAge,30)
                .orderByDesc(User::getId);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

}
