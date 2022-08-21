package com.atguigu;

import com.atguigu.entity.User;
import com.atguigu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.javassist.expr.NewArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class GgktMpDemoApplicationTests {

    //注入mapper
    // Autowired、Resource、Qualifier都可以
    @Autowired
    private UserMapper userMapper;

    //查询表中user表中的所有数据
    @Test
    public void findAll() {
        List<User> userList = userMapper.selectList(null);
        for (User user:userList) {
            System.out.println(user);
        }
    }

    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        //自动生成id，不需要手动set值
        user.setName("test05");
        user.setAge(29);
        user.setEmail("huhu@email.com");
        int rows = userMapper.insert(user);
        System.out.println("rows："+rows);
        //添加成功后，会自动将id回填至user对象中
        System.out.println(user);
    }

    //更新用户信息
    @Test
    public void updateUser(){
        //获取用户信息
        User user = userMapper.selectById(1560677572100984834L);
        //修改用户信息
        user.setName("New Name");
        //更新数据库
        int rows = userMapper.updateById(user);
        System.out.println("rows:"+rows);
    }

    /**
     * mp实现分页
     * 第一步：配置分页插件
     * （1）创建配置类
     * 第二步：按照mp规范代码编写分页
     */
    @Test
    public void findPage(){
        //创建Page对象，传递两个参数：当前页、每页显示的记录数
        Page<User> page = new Page<>(1,3);
        //调用mp方法实现分页
        userMapper.selectPage(page,null);

        //Page继承自IPage,可以创建一个变量来接收list，不创建也可以，mp执行完本身就会回写page
        //IPage<User> pageModel = userMapper.selectPage(page,null);

        //每页的集合
        List<User> list = page.getRecords();
        System.out.println(list);
        //System.out.println(list.size());
        //当前页
        System.out.println(page.getCurrent());
        //总记录数
        System.out.println(page.getTotal());
        //总页数
        System.out.println(page.getPages());
        //每页显示多少条记录
        System.out.println(page.getSize());
        //是否有下一页
        System.out.println(page.hasNext());
        //是否有上一页
        System.out.println(page.hasPrevious());

    }

    //id删除
    @Test
    public void deleteId(){
        int rows = userMapper.deleteById(1560945802854047746L);
        System.out.println(rows);
    }

    //批量删除
    @Test
    public void deleteBatchId(){
        int rows = userMapper.deleteBatchIds(Arrays.asList(1, 3));
        System.out.println(rows);
    }

}
