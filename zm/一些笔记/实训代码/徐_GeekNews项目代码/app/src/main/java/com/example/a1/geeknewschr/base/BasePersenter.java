package com.example.a1.geeknewschr.base;

import java.util.ArrayList;

/**
 * Created by 1 on 2019/4/30.
 */

public abstract class BasePersenter<V extends BaseView> {
    public ArrayList<BaseModel> models = new ArrayList<>();

    protected V mView;

    public void bind(V view){
        this.mView = view;
    }
    public BasePersenter(){
        initModel();
    }
    protected abstract void initModel();


}
