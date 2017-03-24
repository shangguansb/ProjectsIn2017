package com.qunar.fresh.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by kingsley.zhang on 2017/3/21.
 */
public enum Gender {
    UNKNOWN(0, "未知"), MALE(1, "男"), FEMALE(2, "女");

    private int id;
    private String name;
    private static final Map<Integer, Gender> map = Maps.newHashMap();
    static {
        for (Gender gender : values()) {
            map.put(gender.getId(), gender);
        }
    }

    Gender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int code() {
        return this.getId();
    }

    public static Gender codeOf(int id) {
        return map.get(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
