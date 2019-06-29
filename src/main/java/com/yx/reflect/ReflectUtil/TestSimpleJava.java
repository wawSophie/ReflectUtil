package com.yx.reflect.ReflectUtil;
import java.util.Scanner;
/**
 * Author:Sophie
 * Created: 2019/6/28
 */

/**
 * 实现：
 * 1、创建一个类，包括员工姓名，员工工号，员工年龄，员工性别
 * 2、实现一个反射工具类
 *      2.1、首先将  属性名：属性值 | 属性名：属性值 这个字符串
 * 进行解析，将属性名先变为首字母大写，然后再变成set属性名的形式
 * 然后将其加入map的key值，将属性值加入到value值，也就都放到了map中
 *      2.2、创建一个Emp的对象，通过getDeclareMethods方法将emp的setXxx方法
 * 取出来，遍历Map，如果map中存在setXxx方法，则将其值通过这个key取出来，然后再invoke
 * 这个方法，给Xxx属性设置值，
 * 3、打印输出emp
 *
 */

public class TestSimpleJava {
    public static void main(String[] args) {

            System.out.println("请输入员工信息，格式如下 属性名：属性值|属性名：属性值...的方式，输入quit退出");
            Scanner in = new Scanner(System.in);
            String input = in.next();
                Emp<Integer> emp = new Emp<>();
                reflectUtil.reflectUtil(input, emp);
                System.out.println(emp);
            }
        }



