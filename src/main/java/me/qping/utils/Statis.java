package me.qping.utils;

import java.util.*;

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
    List<Object> dataList;

    static Map<String, Statis> map = new LinkedHashMap<>();

    public static Statis get(String key){
        if(key == null) return null;
        if(map.get(key) == null){
            Statis statis = new Statis();
            statis.key = key;
            statis.reset();
            map.put(key, statis);
            return statis;
        }
        return map.get(key);
    }


    public Statis reset(){
        count = 0;
        if(dataList != null){
            dataList.clear();
        }
        dataList = new ArrayList<>();
        return this;
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

    public Statis addData(Object data){
        dataList.add(data);
        count ++;
        return this;
    }

    public List getDataList(){
        return dataList;
    }

    public int getCount(){
        return count;
    }

    public String getKey(){
        return key;
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

    public static Collection<Statis> getAll(){
        return map.values();
    }
}
