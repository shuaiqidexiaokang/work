package com.lzk.relationship.m2m_fk;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int sid;
    private String gender;
    private Date birthday;
    private String major;

    @ManyToMany
    @JoinTable(name="student_teacher",
            joinColumns = {@JoinColumn(name = "sid")},
            inverseJoinColumns = {@JoinColumn(name = "tid")})
    private Set<Teacher> teachers;

    public Student() {
    }

    public Student(String gender, Date birthday, String major) {
        this.gender = gender;
        this.birthday = birthday;
        this.major = major;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
