package com.jiyun.day03.builder;

import android.os.Build;

/**
 * Created by $lzj on 2019/5/30.
 */
public class Computer {

    public void play(){

    }

    public static class Builder{

        private Computer computer;

        public Builder() {
            computer = new Computer();
        }

        public Builder setOs(String os){
            computer.setOs(os);
            return  this;
        }

        public Builder setColor(String color){
            computer.setColor(color);
            return this;
        }

        public Computer build(){
            return computer;
        }
    }
    private String name;
    private String color;
    private int price;
    private String os;

    private Computer() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", os='" + os + '\'' +
                '}';
    }
}
