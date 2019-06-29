package com.yx.reflect.ReflectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:Sophie
 * Created: 2019/6/29
 */
public class reflectUtil {
    public static void reflectUtil(String input, Object obj){

        Map<String,Object> map=new HashMap<>();
        String[] kvPairs= input.split("\\|");
        for (String kvPair:kvPairs){
            String[] kv=kvPair.split("\\:");
            //将：前面的转为setXxx的形式
//            System.out.println(kv[0]);
            map.put(convertSetter(kv[0]),kv[1]);
        }
        reflectSetter(obj,map);
    }

    public static void reflectSetter(Object emp, Map map){

        Class classz=emp.getClass();
        Method[] methods=classz.getDeclaredMethods();
        for (Method method:methods){
            String methodName=method.getName();
            if (map.containsKey(methodName)){
                //通过Key获取得到value
                Object value= map.get(methodName);
                try {
                    //调用方法，给对象赋值
                    method.invoke(emp,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String cap(String str){
        if (str==null || str.length()==0){
            throw new RuntimeException("str must be bot null");
        }
        return str.substring(0,1).toUpperCase()+(str.length()>1?str.substring(1):"");
    }

    public static String convertSetter(String str){
        if (str==null || str.length()==0){
            throw new RuntimeException("str must be bot null");
        }
        return "set"+cap(str);
    }
}
