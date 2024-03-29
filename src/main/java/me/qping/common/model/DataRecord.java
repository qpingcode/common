package me.qping.common.model;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName DataRecord
 * @Description 返回数据集
 * @Author qping
 * @Date 2020/9/7 18:05
 * @Version 1.0
 **/
public class DataRecord {

    int size = 0;
    Object[] data;
    Map<String, Integer> nameMap = new HashMap<>();

    public DataRecord(){
        Object[] data = new Object[ALLOCATION_SIZE];
    }

    public DataRecord(Object[] row, Map<String, Integer> nameMap) {
        this.data = row;
        this.nameMap = nameMap;
        this.size = row.length;
    }

    public boolean containKey(String name){
        return nameMap.containsKey(name);
    }

    public int size(){
        return size;
    }

    public Object get(int index){
        return data[index];
    }

    public Object get(String name) throws RuntimeException {
        if(!nameMap.containsKey(name)){
            return null;
            // fix：当获取目标表结构以后，来源的表是视图或者sql，导致可能来源的数据集中不包括全部的字段，
            // 此时调用get(name) 方法会报错，此时不需要处理这个问题，只需要返回null即可

//            throw new RuntimeException("数据集不存在指定的列名");
        }
        return data[nameMap.get(name)];
    }


    public String getString(int index){
        Object obj = get(index);
        return obj == null ? null : (String) obj;
    }

    public Integer getInt(int index){
        Object obj = get(index);
        return obj == null ? null : (Integer) obj;
    }

    public String getString(String name){
        Object obj = get(name);
        return obj == null ? null : (String) obj;
    }

    public Integer getInt(String name) {
        Object obj = get(name);
        return obj == null ? null : (Integer) obj;
    }

    public Date getDate(String name){
        Object obj = get(name);
        return obj == null ? null : (Date) obj;
    }

    public void put(String key, Object value){
        if(nameMap.containsKey(key)){
            Integer index = nameMap.get(key);
            data[index] = value;
        }else{
            Integer index = size;
            nameMap.put(key, index);
            data = resize(data, index + 1);
            data[index] = value;
            size++;
        }
    }

    public void put(int index, Object value){
        if(index < 0 || index >= size){
            throw new RuntimeException("index out of range : " + index);
        }
        data[index] = value;
    }

    public Object[] toArray(){
        return Arrays.copyOf(data, size);
    }

    public Object[] toArray(Object... befores) {
        int newLength = size + befores.length;
        Object[] objects = new Object[newLength];
        for (int i = 0; i < befores.length; i++) {
            objects[i] = befores[i];
        }
        System.arraycopy(data, 0 , objects, 0 + befores.length,
                size);
        return objects;
    }

    public Map<String, Object> toMap(){
        Set<String> keyset = nameMap.keySet();
        Map<String, Object> map = new HashMap<>();
        for(String key : keyset){
            map.put(key, data[nameMap.get(key)]);
        }
        return map;
    }

    public static final int ALLOCATION_SIZE = 10;
    public static Object[] resize( Object[] row, int size ) {
        if ( row != null && row.length >= size ) {
            return row;
        }

        Object[] newObj = new Object[size + ALLOCATION_SIZE];
        if ( row != null ) {
            System.arraycopy( row, 0, newObj, 0, row.length );
        }
        return newObj;
    }

    public String toString(){
        return "Record{"+
                "data=" + Arrays.toString(data) +
                ", namemap=" + nameMap +
            "}";
    }

    public static void main(String[] args) {
        DataRecord d = new DataRecord();
        d.put("1", 111);
        d.put("2", 222);
        d.put("3", 333);
        d.put("4", 444);
        d.put("1", 1111);
        d.put("ok", 123123);

//        for(int i = 10 ; i< 99; i++){
//            d.put(i+"",i);
//        }

//        System.out.println(d.getInt("1"));
//        System.out.println(d.get("2"));
//        System.out.println(d.toMap());
//        System.out.println(Arrays.toString(d.toArray()));

        System.out.println(Arrays.toString(d.toArray()));
        System.out.println(Arrays.toString(d.toArray("test", 1, new Test())));



    }

    public static class Test{
        String name ="ok";
        int age = 12;
        public String toString(){
            return "name " + name + " age " + age;

        }
    }
}
