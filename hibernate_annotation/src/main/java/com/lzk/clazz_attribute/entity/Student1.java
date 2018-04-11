package com.lzk.clazz_attribute.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Student1 {
    @EmbeddedId
    private Student1PK pk;
    private String gender;
    private Date birthday;
    private String major;
}
