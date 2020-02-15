package com.veryreader.d2p.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2018/8/21 0021$
 */

@Data
@AllArgsConstructor
@Slf4j
public class DefaultEnumDict implements EnumDict {
    private String name;
    private Integer value;
    private String label;
    private String color;

    public DefaultEnumDict(String name, Integer value, String label) {
        this.name = name;
        this.value = value;
        this.label = label;

        this.color = randomColor(this.name);
    }

    public DefaultEnumDict(EnumDict enumDict) {
        this.name = enumDict.getName();
        this.value = enumDict.getValue();
        this.color = enumDict.getColor();
        this.label = enumDict.getLabel();
    }

    public static List<EnumDict> create(Class clazz){
        if(!clazz.isEnum()){
            return null;
        }
        try {
            Method method = clazz.getMethod("values");
            EnumDict[] enums = (EnumDict[]) method.invoke(null);
            List<EnumDict> list = new ArrayList<>(enums.length);
            for (EnumDict item : enums) {
                list.add(new DefaultEnumDict(item));
            }
            return list;
        } catch (Exception e) {
            log.error("枚举类型转化失败:{},{}",e.getMessage(),clazz.getName(),e);
        }
        return null;
    }


}
