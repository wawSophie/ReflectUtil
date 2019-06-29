package com.yx.reflect.ReflectUtil;

/**
 * Author:Sophie
 * Created: 2019/6/29
 */
class Emp<T>{
    private String name;
    private String num;
    private T age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public T getAge() {
        return age;
    }

    public void setAge(T age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
