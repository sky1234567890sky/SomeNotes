package com.example.day04_homework;

import android.widget.ImageView;

/**
 * Created by 苏克阳 on 2019/3/6.
 */

class Student {

    String name;
    String content;
    int img;

    public Student(String name, String content, int img) {
        this.name = name;
        this.content = content;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
