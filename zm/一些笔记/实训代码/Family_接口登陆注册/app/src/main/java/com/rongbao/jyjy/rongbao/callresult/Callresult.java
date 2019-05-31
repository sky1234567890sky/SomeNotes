package com.rongbao.jyjy.rongbao.callresult;

/**
 * Created by Administrator on 2019/4/15 0015.
 */

public interface Callresult<T> {
    void Onsuccessful(T bean);
    void OnFaild(String messge);
}
