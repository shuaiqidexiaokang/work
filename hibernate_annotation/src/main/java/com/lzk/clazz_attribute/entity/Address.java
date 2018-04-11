package com.lzk.clazz_attribute.entity;

import javax.persistence.Embeddable;

@Embeddable
//表示一个非Entity类可以嵌入到另一个Entity类中作为属性而存在。
public class Address {
    private String postCode;
    private String address;
    private String phone;

    public Address() {
    }

    public Address(String postCode, String address, String phone) {
        this.postCode = postCode;
        this.address = address;
        this.phone = phone;
    }
}
