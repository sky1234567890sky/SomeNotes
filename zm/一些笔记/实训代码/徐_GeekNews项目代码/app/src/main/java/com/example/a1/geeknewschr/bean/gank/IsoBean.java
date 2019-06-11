package com.example.a1.geeknewschr.bean.gank;

import java.util.List;

/**
 * Created by 1 on 2019/4/22.
 */

public class IsoBean {

    /**
     * error : false
     * results : [{"_id":"5ca173459d21225de91ac03f","createdAt":"2019-04-01T02:11:17.218Z","desc":"跨平台、全开源的野火IM解决方案的iOS部分，是一个完整的IM。","publishedAt":"2019-04-10T08:22:09.41Z","source":"web","type":"iOS","url":"https://github.com/wildfirechat/ios-chat","used":true,"who":"SwiftyWang"},{"_id":"5c98e2f89d21225def25412e","createdAt":"2019-03-25T14:17:28.964Z","desc":"QAPM是去哪儿使用的APP监控系统。已在内部稳定运行3年。","publishedAt":"2019-03-25T14:18:12.570Z","source":"web","type":"iOS","url":"https://github.com/qunarcorp/qapm_ios","used":true,"who":"SwiftyWang"},{"_id":"5c47cb309d212264d18bb276","createdAt":"2019-01-23T02:02:24.827Z","desc":"一句代码就可为你的数据添加一级和二级目录缓存，支持单独删除指定文件下的文件","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p975r4krg30qo0hs18l"],"publishedAt":"2019-02-18T05:55:12.480Z","source":"web","type":"iOS","url":"https://github.com/dudongge/DDGDataCache_OC","used":true,"who":"lijinshanmx"},{"_id":"5c622a949d212243205cc80c","createdAt":"2019-02-18T05:53:18.123Z","desc":"实现所有主流APP分类选择效果，纯swift，简单易用，灵活扩展.","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p97amxdlg309q02879m","https://ww1.sinaimg.cn/large/0073sXn7ly1g1p97b4tz0g309q028n2h","https://ww1.sinaimg.cn/large/0073sXn7ly1g1p97bn7mqg309q0280wx"],"publishedAt":"2019-02-18T05:53:20.699Z","source":"web","type":"iOS","url":"https://github.com/pujiaxin33/JXSegmentedView","used":true,"who":"lijinshanmx"},{"_id":"58e98313421aa9544b773f9d","createdAt":"2017-04-09T08:40:51.874Z","desc":"购物车类的抛物线动画（支持上抛或者下抛） && 阻尼动画（弹球动画）","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p8xy98oyg30ab0jp0yq"],"publishedAt":"2019-02-13T03:02:27.510Z","source":"web","type":"iOS","url":"https://github.com/jinht/ShopCarAnimation","used":true,"who":"lijinshanmx"},{"_id":"58e98325421aa954511ebe47","createdAt":"2017-04-09T08:41:09.153Z","desc":"悬浮球/悬浮按钮/辅助按钮（类似于iOS系统自带的AssistiveTouch/京东/聚划算/建行等的辅助按钮）","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p8xz1kgcg30aa0igwg2"],"publishedAt":"2019-02-13T03:02:12.359Z","source":"web","type":"iOS","url":"https://github.com/jinht/FloatingBall","used":true,"who":"lijinshanmx"},{"_id":"5c403e919d212264d4501d30","createdAt":"2019-01-17T08:36:33.526Z","desc":"一个完善的iOS UI敏捷开发框架，基于UIKit，包含常用控件的链式API拓展、一个数据驱动的列表框架、一个事件处理队列。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze97l9tq7g30ad0ih1ky","https://ww1.sinaimg.cn/large/0073sXn7ly1fze97p50emg30ad0ihkjl"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/tbl00c/ZZFLEX","used":true,"who":"夜尽天明"},{"_id":"5c45754b9d212264cbcc5bda","createdAt":"2019-01-21T07:31:23.953Z","desc":"一个iOS菜单。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98jrz88j31po0mix1b"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/TwoLivesLeft/Menu","used":true,"who":"lijinshanmx"},{"_id":"5c4575829d212264ce006f47","createdAt":"2019-01-21T07:32:18.570Z","desc":"三行代码组件化集成 Flutter！可用于已有 iOS 项目，对原工程无侵入，无需更改原项目配置，集成后可直接以组件形式开发 Flutter 业务。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98lym9lg308r0hph7t"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/jiisd/YHFlutterAdapter","used":true,"who":"lijinshanmx"},{"_id":"5c4575989d212264d4501d3d","createdAt":"2019-01-21T07:32:40.819Z","desc":"秒级! 三行代码实现iOS视频压缩、变速、混音、合并、水印、旋转、换音、裁剪 ! 支持不同分辩率，支持你能想到的各种混合操作! 更多功能不断增加中... iOS 8.0 +","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1fze98nrqklg30ax0izh4o","https://ww1.sinaimg.cn/large/0073sXn7ly1fze98puthvg30aj0i3nkp"],"publishedAt":"2019-01-21T00:00:00.0Z","source":"chrome","type":"iOS","url":"https://github.com/CoderHenry66/WAVideoBox","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

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
         * _id : 5ca173459d21225de91ac03f
         * createdAt : 2019-04-01T02:11:17.218Z
         * desc : 跨平台、全开源的野火IM解决方案的iOS部分，是一个完整的IM。
         * publishedAt : 2019-04-10T08:22:09.41Z
         * source : web
         * type : iOS
         * url : https://github.com/wildfirechat/ios-chat
         * used : true
         * who : SwiftyWang
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p975r4krg30qo0hs18l"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
