/**
 * Copyright (C) 2016-2017 Hangzhou Elabcare Co. Ltd.
 * All right reserved.
 *
 * @author: Simon.lee
 * date: 2017-10-09 12:24
 */
package com.xjhh.framework.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * 缓存 DTO
 */
 public class Cache {
    @Getter
    @Setter
    /**
     * 缓存ID
     */
    private String key;
    @Getter
    @Setter
    /**
     * 缓存数据
     */
    private Object value;
    @Getter
    @Setter
    /**
     * 更新时间
     */
    private long timeOut;
    @Getter
    @Setter
    /**
     * 是否终止
     */
    private boolean expired;

    public Cache() {
        super();
    }
    public Cache(String key, Object value, long timeOut, boolean expired) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
        this.expired = expired;
    }
}
