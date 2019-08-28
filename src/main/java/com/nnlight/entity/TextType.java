package com.nnlight.entity;

/**
 * 刷新文件的类型
 */
public enum TextType {
    IMEI(0, "IMEI"), AREA(1, "AREA"), BOX(2, "BOX"), SPACE(3,"动作间隔");

    private int value;
    private String name;

    TextType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TextType getTextTypeByValue(int value) {
        for (TextType type : values()) {
            if (value == type.value) return type;
        }
        return IMEI;
    }

    public static TextType getTextTypeByName(String name) {
        for (TextType type : values()) {
            if (type.name.equals(name)) return type;
        }
        return IMEI;
    }
}
