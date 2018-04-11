package com.lzk.relationship.o2o_bfk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class IdCard {
    @Id
    @GeneratedValue(generator = "pid")
    @GenericGenerator(name="pid",strategy = "assigned")
    @Column(length = 18)
    private String pid;
    private String sname;

    @OneToOne(mappedBy = "card")
    private Student student;

    public IdCard() {

    }

    public IdCard(String pid, String sname) {
        this.pid = pid;
        this.sname = sname;
    }
}
