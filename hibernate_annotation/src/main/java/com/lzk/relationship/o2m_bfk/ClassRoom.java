package com.lzk.relationship.o2m_bfk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ClassRoom {
    @Id
    @GeneratedValue(generator = "cid")
    @GenericGenerator(name="cid",strategy = "assigned")
    @Column(length = 4)
    private String cid;
    private String cname;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="cid")
    private Set<Student> studentSet;
}
