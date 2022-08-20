package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    //@TableId(type=IdType.ASSIGN_UUID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
