package com.jiyun.demo2.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by $lzj on 2019/4/8.
 */
@Entity
public class DbBean {

    @Id(autoincrement = true)
    private Long id;

    @Property
    private String text;

    @Property
    private String name;

    @Property
    private String thumbnail;

    @Generated(hash = 49011383)
    public DbBean(Long id, String text, String name, String thumbnail) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    @Generated(hash = 1953169116)
    public DbBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
