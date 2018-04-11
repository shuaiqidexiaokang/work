package com.lzk.clazz_attribute.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Student1PK implements Serializable{
    private String id;
    private String sid;
}
