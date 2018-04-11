package com.lzk.relationship.o2m_fk;

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

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="cid",referencedColumnName = "cid")
    private ClassRoom classRoom;
}
