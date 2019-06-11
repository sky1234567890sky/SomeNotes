package com.example.a1.geeknewschr.bean;

import java.io.Serializable;

/**
 * Created by 1 on 2019/4/19.
 */

public class GoldShowBean implements Serializable{
    public String title;
    public boolean isChecked;

    public GoldShowBean(String pTitle, boolean pIsChecked) {
        title = pTitle;
        isChecked = pIsChecked;
    }
}
