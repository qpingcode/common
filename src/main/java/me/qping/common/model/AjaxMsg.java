package me.qping.common.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by QPing on 2015/12/11.
 */
public class AjaxMsg {
    public static String CODE_SUCCESS = "success";
    public static String CODE_FAIL = "fail";

    String code;
    String msg;
    Object data;
    Map<String, Object> body = new LinkedHashMap();//封装json的map

    public AjaxMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public AjaxMsg setData(Object data) {
        this.data = data;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public AjaxMsg put(String key, Object value){
        //向json中添加属性，在js中访问，请调用data.map.key
        body.put(key, value);
        return this;
    }

    public AjaxMsg remove(String key){
        body.remove(key);
        return this;
    }

    public static AjaxMsg success() {
        return new AjaxMsg(CODE_SUCCESS, "操作成功！");
    }

    public static AjaxMsg fail() {
        return new AjaxMsg(CODE_FAIL, "操作失败！");
    }

}
