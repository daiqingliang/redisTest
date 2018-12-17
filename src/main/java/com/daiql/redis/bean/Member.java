package com.daiql.redis.bean;

import java.io.Serializable;

public class Member implements Serializable {
    private String mid;
    private String age;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Member [mid=" + mid + ", age=" + age + "]";
    }
}
