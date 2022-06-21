package com.jiangzhiyan.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static final Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001","张三");
        hashMap.put("10002","李四");
        hashMap.put("10003","王二");
        hashMap.put("10004","Rose");
    }

    public String queryUsername(String userId){
        return hashMap.get(userId);
    }
}
