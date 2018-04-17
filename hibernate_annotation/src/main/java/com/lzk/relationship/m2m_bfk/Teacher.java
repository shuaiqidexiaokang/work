package com.lzk.relationship.m2m_bfk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "tid")
    @GenericGenerator(name="tid",strategy = "assigned")
    @Column(length = 4)
    private String tid;
    private String tname;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.EAGER)
    private Set<Student> students;

    public Teacher() {
    }

    public Teacher(String tid,String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }
}
