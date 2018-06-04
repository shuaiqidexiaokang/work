package com.lzk.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Person")
public class Person implements Serializable {

    @Id
    private String id;

    @Column(name = "created")
    private long created = System.currentTimeMillis();

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "remark")
    private String remark;

    public Person() {
    }

    public Person(String id, String username, String address, String phone, String remark) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.remark = remark;
    }
}