package com.veryreader.d2p.api.modules.permission.enums;

import com.veryreader.d2p.api.model.vo.EnumDict;

public enum ResourceTypeEnum implements EnumDict {
    MENU(1,"菜单","info"),
    BTN(2,"按钮","success"),
    ROUTE(3,"路由","warning");

    private Integer value;
    private String color;
    private String label;

    ResourceTypeEnum(int value, String label, String color) {
        this.value = value;
        this.label = label;
        this.color = color;
    }

    public Integer getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getName() {
        return name();
    }
}
