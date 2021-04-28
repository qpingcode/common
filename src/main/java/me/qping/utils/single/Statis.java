package me.qping.utils.single;

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
    int dataMaxSize = -1;


    int printAfterTimes = -1;
    public Statis printAfter(int perTimes){
        printAfterTimes = perTimes;
        return this;
    }

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

    public static void resetAll(){
       map.clear();
    }


    public Statis reset(){
        count = 0;
        if(dataList != null){
            dataList.clear();
        }
        dataList = new ArrayList<>();
        return this;
    }

    public Statis setDataMaxSize(int i ){
        this.dataMaxSize = i;
        return this;
    }

    public Statis increase(){
        count ++;
        printLineInConsole();
        return this;
    }

    private void printLineInConsole(){
        if(printAfterTimes > -1){
            if(count % printAfterTimes == 0){
                System.out.println(key + " : " + count);
            }
        }
    }


    public Statis decrease(){
        count --;
        return this;
    }

    public Statis increase(int i){
        count = count + i;
        printLineInConsole();
        return this;
    }


    public Statis decrease(int i){
        count = count - i;
        return this;
    }

    public Statis addData(Object data){
        count ++;
        printLineInConsole();
        if(dataMaxSize > -1 && dataList.size() > (dataMaxSize - 1)){
            return this;
        }

        dataList.add(data);
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
