package com.lzk.relationship.o2o_fk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IdCard {
    @Id
    @GeneratedValue(generator = "pid")
    @GenericGenerator(name="pid",strategy = "assigned")
    @Column(length = 18)
    private String pid;
    private String sname;

    public IdCard() {

    }

    public IdCard(String pid, String sname) {
        this.pid = pid;
        this.sname = sname;
    }
}
