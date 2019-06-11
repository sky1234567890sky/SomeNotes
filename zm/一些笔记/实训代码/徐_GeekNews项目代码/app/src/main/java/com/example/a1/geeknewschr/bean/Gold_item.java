package com.example.a1.geeknewschr.bean;

import java.util.List;

/**
 * Created by 1 on 2019/5/3.
 */

public class Gold_item {
    /**
     * count : 10
     * error : false
     * results : [{"desc":"Archi - 用三种 Android 代码架构来重构一个应用","ganhuo_id":"56cc6d22421aa95caa70792d","publishedAt":"2015-10-15T11:01:18.103000","readability":"","type":"Android","url":"https://github.com/ivacf/archi","who":"AllenJuns"},{"desc":"基于Facebook Buck改造Android构建系统之初体验","ganhuo_id":"56cc6d22421aa95caa707930","publishedAt":"2015-10-20T03:45:36.970000","readability":"","type":"Android","url":"http://www.jianshu.com/p/1e990aac7836","who":"AllenJuns"},{"desc":"侧滑菜单流动效果","ganhuo_id":"56cc6d23421aa95caa707959","publishedAt":"2015-10-23T04:01:16.217000","readability":"","type":"Android","url":"https://github.com/mxn21/FlowingDrawer","who":"Jason"},{"desc":"一个垂直的ViewPager","ganhuo_id":"56cc6d22421aa95caa707948","publishedAt":"2015-10-19T03:47:21.650000","readability":"","type":"Android","url":"https://github.com/kaelaela/VerticalViewPager","who":"有时放纵"},{"desc":"给 App 提速：Android 性能优化总结","ganhuo_id":"56cc6d23421aa95caa707985","publishedAt":"2015-11-11T03:47:41.142000","readability":"","type":"Android","url":"http://android.jobbole.com/81944/","who":"有时放纵"},{"desc":"一个简化 Android 6.0 上权限处理的库。","ganhuo_id":"56cc6d23421aa95caa7079b7","publishedAt":"2015-11-17T04:00:01.763000","readability":"","type":"Android","url":"https://github.com/k0shk0sh/PermissionHelper","who":"Ailurus"},{"desc":"不同类型的loading动画效果","ganhuo_id":"56cc6d1d421aa95caa7077ec","publishedAt":"2015-08-25T04:08:30.731000","readability":"","type":"Android","url":"https://github.com/raweng/android-different-loading-animations","who":"Jason"},{"desc":"activity下各种状态的显示","ganhuo_id":"56cc6d1d421aa95caa7077f5","publishedAt":"2015-08-26T03:51:54.212000","readability":"","type":"Android","url":"https://github.com/vlonjatg/progress-activity","who":"Jason"},{"desc":"利用注解自动生成Gson\u2018s Model的库","ganhuo_id":"56cc6d1d421aa95caa7077fe","publishedAt":"2015-08-27T03:52:53.839000","readability":"","type":"Android","url":"https://github.com/tianzhijiexian/JsonAnnotation","who":"有时放纵"},{"desc":"不重启实现日夜间模式切换","ganhuo_id":"56cc6d1d421aa95caa707857","publishedAt":"2015-09-07T03:51:26.505000","readability":"","type":"Android","url":"https://github.com/zzz40500/ThemeDemo","who":"鲍永章"}]
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc : Archi - 用三种 Android 代码架构来重构一个应用
         * ganhuo_id : 56cc6d22421aa95caa70792d
         * publishedAt : 2015-10-15T11:01:18.103000
         * readability :
         * type : Android
         * url : https://github.com/ivacf/archi
         * who : AllenJuns
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
