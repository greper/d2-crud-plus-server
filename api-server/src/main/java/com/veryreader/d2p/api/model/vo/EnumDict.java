package com.veryreader.d2p.api.model.vo;

public interface EnumDict {
    Integer getValue();
    String getLabel();
    String getName();
    String getColor();

    default String randomColor(String value){
        int colorInt = value.hashCode();
        return randomColor(colorInt);
    }

    default String randomColor(int value){
        int colorInt = value%5;
        if(colorInt ==0){
            return "primary";
        }else if(colorInt ==1){
            return "success";
        }else if(colorInt ==2){
            return "info";
        }else if(colorInt ==3){
            return "danger";
        }else if(colorInt ==4){
            return "warning";
        }
        return "info";
    }

}
