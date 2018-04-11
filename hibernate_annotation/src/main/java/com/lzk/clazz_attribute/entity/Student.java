package com.lzk.clazz_attribute.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity//映射实体类
@Table(name = "t_student",schema = "hibernate_annotation")//只能标注在实体类的class定义处，表示实体对应的数据库表的信息。
public class Student {
    @Id
    @GeneratedValue(generator = "sid")
    @GenericGenerator(name="sid",strategy = "assigned")
    private int sid;
    private String name;
    @Column(name = "sex",unique = true,length = 5)
    private String gender;
    private Date birthday;
    private String major;
    @Embedded
    private Address address;
    @Transient
    private double salary;


    public Student() {
    }

    public Student(int sid,String name, String gender, Date birthday, String major, Address address) {
        this.sid = sid;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.major = major;
        this.address = address;
    }
}
