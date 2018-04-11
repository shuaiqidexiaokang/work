package com.lzk.relationship.m2m_fk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "tid")
    @GenericGenerator(name="tid",strategy = "assigned")
    @Column(length = 4)
    private String tid;
    private String tname;

    public Teacher() {
    }

    public Teacher(String tid,String tname) {
        this.tid = tid;
        this.tname = tname;
    }
}
