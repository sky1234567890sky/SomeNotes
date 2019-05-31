package com.jiyun.demo1;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by $lzj on 2019/4/4.
 */
@Entity
public class Person {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private String name;

    @Property
    private int age;

    @Property
    private Long tel;

    @Property
    private String address;

    @Generated(hash = 2045251450)
    public Person(Long id, String name, int age, Long tel, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.address = address;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getTel() {
        return this.tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tel=" + tel +
                ", address='" + address + '\'' +
                '}';
    }
}
