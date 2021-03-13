package me.qping.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Statis
 * @Description 统计辅助类
 * @Author qping
 * @Date 2021/3/10 11:43
 * @Version 1.0
 **/
public class Statis {

    int count;
    String key;

    static Map<String, Statis> map = new HashMap<>();

    public static Statis get(String key){
        if(key == null) return null;
        if(map.get(key) == null){
            Statis statis = new Statis();
            statis.key = key;
            statis.count = 0;

            map.put(key, statis);
            return statis;
        }
        return map.get(key);
    }



    public Statis increase(){
        count ++;
        return this;
    }


    public Statis decrease(){
        count --;
        return this;
    }

    public Statis increase(int i){
        count = count + i;
        return this;
    }


    public Statis decrease(int i){
        count = count - i;
        return this;
    }

    @Override
    public String toString() {
        return "Statis{" +
                " key='" + key + '\'' +
                ", count=" + count +
                '}';
    }

    public static void printAll(){
        for(Statis statis : map.values()){
            System.out.println(statis);
        }
    }
}
