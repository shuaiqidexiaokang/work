package com.lzk.relationship.o2o_fk;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int sid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pid")
    private IdCard card;
    private String gender;
    private Date birthday;
    private String major;

    public Student() {
    }

    public Student(IdCard card, String gender, Date birthday, String major){
        this.card = card;
        this.gender = gender;
        this.birthday = birthday;
        this.major = major;
    }
}
