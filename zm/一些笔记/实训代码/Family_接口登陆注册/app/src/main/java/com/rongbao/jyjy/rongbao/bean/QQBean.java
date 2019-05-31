package com.rongbao.jyjy.rongbao.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/4/22 0022.
 */

public class QQBean implements Serializable {
    public String  unionid;
    public String  is_yellow_vip;
    public String  screen_name;
    public String  msg;
    public String  vip;
    public String  city;
    public String accessToken;
    public String gender;
    public String province;
    public String is_yellow_year_vip;
    public String openid;
    public String profile_image_url;
    public String  yellow_vip_level;
    public String  access_token;
    public String  iconurl;
    public String  name;
    public String  uid;
    public String  expiration;
    public String  expires_in;
    public String  level;
    public String  ret;

    public QQBean() {
    }

    public QQBean(String unionid, String is_yellow_vip, String screen_name, String msg, String vip, String city, String accessToken, String gender, String province, String is_yellow_year_vip, String openid, String profile_image_url, String yellow_vip_level, String access_token, String iconurl, String name, String uid, String expiration, String expires_in, String level, String ret) {
        this.unionid = unionid;
        this.is_yellow_vip = is_yellow_vip;
        this.screen_name = screen_name;
        this.msg = msg;
        this.vip = vip;
        this.city = city;
        this.accessToken = accessToken;
        this.gender = gender;
        this.province = province;
        this.is_yellow_year_vip = is_yellow_year_vip;
        this.openid = openid;
        this.profile_image_url = profile_image_url;
        this.yellow_vip_level = yellow_vip_level;
        this.access_token = access_token;
        this.iconurl = iconurl;
        this.name = name;
        this.uid = uid;
        this.expiration = expiration;
        this.expires_in = expires_in;
        this.level = level;
        this.ret = ret;
    }

    public QQBean(String screen_name, String profile_image_url) {
        this.screen_name = screen_name;
        this.profile_image_url = profile_image_url;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getIs_yellow_vip() {
        return is_yellow_vip;
    }

    public void setIs_yellow_vip(String is_yellow_vip) {
        this.is_yellow_vip = is_yellow_vip;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIs_yellow_year_vip() {
        return is_yellow_year_vip;
    }

    public void setIs_yellow_year_vip(String is_yellow_year_vip) {
        this.is_yellow_year_vip = is_yellow_year_vip;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getYellow_vip_level() {
        return yellow_vip_level;
    }

    public void setYellow_vip_level(String yellow_vip_level) {
        this.yellow_vip_level = yellow_vip_level;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        return "QQBean{" +
                "unionid='" + unionid + '\'' +
                ", is_yellow_vip='" + is_yellow_vip + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", msg='" + msg + '\'' +
                ", vip='" + vip + '\'' +
                ", city='" + city + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", is_yellow_year_vip='" + is_yellow_year_vip + '\'' +
                ", openid='" + openid + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", yellow_vip_level='" + yellow_vip_level + '\'' +
                ", access_token='" + access_token + '\'' +
                ", iconurl='" + iconurl + '\'' +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", expiration='" + expiration + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", level='" + level + '\'' +
                ", ret='" + ret + '\'' +
                '}';
    }
}
