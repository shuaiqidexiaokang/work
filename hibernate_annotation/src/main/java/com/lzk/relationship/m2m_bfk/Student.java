package com.lzk.relationship.m2m_bfk;

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

    @ManyToMany(fetch = FetchType.EAGER)
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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", major='" + major + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
