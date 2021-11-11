package com.padingpading.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * @author libin
 * @description
 * @date 2021/9/24
 */
public class User extends  BaseEntity{
    private Integer id;

    private String name;

    List<String> stringList;

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this)
//                .add("locations", id)
//                .add("name",name)
//                .toString();
//    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("locations", id)
                .add("name",name)
                .toString();
    }
    public static void main(String[] args) {
        User user = new User();
        user.setName("1");
        user.setId(1);
        User user1 = new User();
        user1.setName("2");
        user1.setId(2);
        OperationContent<User> userOperationContent = new OperationContent<User>();
        String build = new OperationContent<User>()
                .update(user,user1)
                .build();
        System.out.println(build);
    }


}
