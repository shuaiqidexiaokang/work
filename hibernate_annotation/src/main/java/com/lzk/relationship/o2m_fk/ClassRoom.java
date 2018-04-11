package com.lzk.relationship.o2m_fk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClassRoom {
    @Id
    @GeneratedValue(generator = "cid")
    @GenericGenerator(name="cid",strategy = "assigned")
    @Column(length = 4)
    private String cid;
    private String cname;
}
