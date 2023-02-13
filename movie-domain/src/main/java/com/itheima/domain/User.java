package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//管理员
@Data
@TableName("tb_user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;//主键

    private String username;//用户名

    private String password;//密码
}
