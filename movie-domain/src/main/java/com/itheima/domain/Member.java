package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//会员
@Data
@TableName("tb_member")
public class Member implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;//主键

    private String username;//用户名

    private String password;//密码

    private String phone;//手机
}
