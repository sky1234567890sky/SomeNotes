package com.rongbao.jyjy.rongbao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2019/4/24 0024.
 */
@Entity
public class MyDB_Login_Bean {
    @Id
    @Unique
    public Long id;
    public String  username;
    public int   image;
    public String userpsd;
    @Generated(hash = 761729021)
    public MyDB_Login_Bean(Long id, String username, int image, String userpsd) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.userpsd = userpsd;
    }
    @Generated(hash = 1675939758)
    public MyDB_Login_Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getImage() {
        return this.image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getUserpsd() {
        return this.userpsd;
    }
    public void setUserpsd(String userpsd) {
        this.userpsd = userpsd;
    }

    @Override
    public String toString() {
        return "MyDB_Login_Bean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", image=" + image +
                ", userpsd='" + userpsd + '\'' +
                '}';
    }
}
