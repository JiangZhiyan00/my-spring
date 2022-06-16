package com.jiangzhiyan.springframework.test.bean;

public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void query(){
        System.out.println("查询用户成功!");
    }
}
