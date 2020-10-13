package me.qping.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Designed For
 *
 * @auther:you
 * @date:2018/11/13 上午11:36
 */

public class MapUtils {

    public static Map<String, Object> of(Object... kv){

        if(kv != null && kv.length % 2 > 0){
            throw new RuntimeException("键值对数量必须匹配");
        }

        Map<String, Object> map = new HashMap<>();
        if(kv != null && kv.length > 0){
            int len = kv.length / 2;

            for(int i = 0; i < len; i++){
                String k = (String) kv[2 * i];

                if(k == null){
                    throw new RuntimeException("key必须有值");
                }

                Object v = kv[2 * i + 1];
                map.put(k, v);
            }
        }
        return map;
    }
}
