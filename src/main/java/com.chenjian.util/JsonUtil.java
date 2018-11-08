package com.chenjian.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class JsonUtil {

    private static Logger log=Logger.getLogger(JsonUtil.class.getName());

    public static Class<JSON> getJsonBean(){
        return JSON.class;
    }


    public  static <T> T stringToRealObject(String jsonString, Class<T> clazz){

        if(jsonString==null) return null;

        try{
            return JSON.parseObject(jsonString, new TypeReference<T>(){});
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public  static <T> T stringToTypeReference(String jsonString, TypeReference<T> type){

        if(jsonString==null || "".equals(jsonString.trim()) ||type==null) return null;

        try{
            return JSON.parseObject(jsonString, type);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String objectToString(Object obj){
        if(obj==null)return "";
        return JSON.toJSONString(obj);
    }

    public static Object stringToObject(String json,Class cls){

        if(json==null) return null;
        try{
            return JSON.parseObject(json,cls);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public  static   <T> List<T> fastStringToArry(String json, Class<T> t){
        if(json==null)return null;
        List<T> array=JSON.parseArray(json, t);
        return array;
    }

    public static HashMap<String,Object> readJson(String fileurl){
        HashMap<String,Object> values=new HashMap<String,Object>();

        try {
            ObjectMapper mapper=new ObjectMapper();
            values=mapper.readValue(new File(fileurl), HashMap.class);
            return values;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values;
    }
}
