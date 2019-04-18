package com.zyz.hawkeye.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@UtilityClass
public class JSONUtil {

    public Boolean isJson(String content) {
        //进行简单过滤.
        if (StringUtils.isBlank(content)) {
            return Boolean.FALSE;
        }
        if (!content.startsWith("{") && !content.startsWith("[")) {
            return Boolean.FALSE;
        }

//        Object obj = null;
//        try {
//            obj = new JSONTokener(content).nextValue();
//        } catch (JSONException e) {
//            //do nothing
//        }
//        return obj instanceof JSONObject || obj instanceof JSONArray;
        return Boolean.TRUE;
    }

    public List<String> flatToList(String prefix, JSONObject jsonObject){
        List<String> resultList = Lists.newLinkedList();
        jsonObject.forEach((k,v)->{
            //只平铺普通对象
            if(v instanceof JSONObject){
                String allPrefix = prefix + k + ".";
                resultList.addAll(flatToList(allPrefix, (JSONObject) v));
            } else {
                resultList.add(prefix + k);
            }
        });
        return resultList;
    }

    public Map<String, Object> flat(String prefix, JSON json){
        Map<String, Object> resultMap = Maps.newHashMap();
        final String currentPrefix;
        if(json instanceof JSONObject) {
            currentPrefix = StringUtils.isNotBlank(prefix) ? prefix + "." : "";
            JSONObject jsonObject = (JSONObject) json;
            jsonObject.forEach((k, v) -> {
                if (v instanceof JSON) {
                    String allPrefix = currentPrefix + k;
                    resultMap.putAll(flat(allPrefix, (JSON) v));
                } else {
                    resultMap.put(currentPrefix + k, v);
                }
            });
        }else if(json instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) json;
            for (int i = 0; i < jsonArray.size(); i++) {
                Object objAtI = jsonArray.get(i);
                String allPrefix = prefix + "[" + i + "]";
                if(objAtI instanceof JSON){
                    resultMap.putAll(flat(allPrefix, (JSON)objAtI));
                } else {
                    resultMap.put(allPrefix, objAtI);
                }
            }
        }
        return resultMap;
    }
}
