package com.yx.Pattern;

/**
 * Author:Sophie
 * Created: 2019/7/10
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){
    }
    public static Singleton getInstance(){
        if (singleton==null){
            singleton=  new Singleton();
        }
        return singleton;
    }

    public static void main(String[] args) {
        Singleton singleton=new Singleton();
        singleton.getInstance();
        System.out.println(singleton);
        Singleton singleton1=new Singleton();
        singleton1.getInstance();
        System.out.println(singleton1);

    }
}
