package com.lzk.relationship.m2o_fk;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int sid;
    private String gender;
    private Date birthday;
    private String major;
}
