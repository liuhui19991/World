package com.liuhui.world.ui.model;

import java.util.List;

/**
 * Created by liuhui on 2017/3/14.
 */

public class NewsListModel extends DataModel {

    /**
     * date : 20170313
     * stories : [{"images":["http://pic3.zhimg.com/5b8e251d6567ece8e1a176119b786eda.jpg"],"type":0,"id":9284733,"ga_prefix":"031322","title":"小事 · 宝贝，宝贝，给你一点甜"},{"images":["http://pic2.zhimg.com/3610ec576110a9cf8ff08f8e3f0704f1.jpg"],"type":0,"id":9284873,"ga_prefix":"031321","title":"《战争天堂》\u2014\u2014 照向炼狱的那道光"},{"images":["http://pic2.zhimg.com/cd79f2c1a89d086b72333d21c9d657d1.jpg"],"type":0,"id":9276303,"ga_prefix":"031320","title":"从医学的角度来看，不结婚有哪些坏处？"},{"images":["http://pic1.zhimg.com/7fc8426b6b15babcfd68d8387e325b04.jpg"],"type":0,"id":9284805,"ga_prefix":"031319","title":"比起黑客，「内鬼」才是信息安全事件的主要来源"},{"images":["http://pic4.zhimg.com/14d8fa29c14609b21f93380b9977e237.jpg"],"type":0,"id":9284234,"ga_prefix":"031318","title":"推荐一部真人秀，看完好想删了朋友圈"},{"images":["http://pic4.zhimg.com/274018abeb31d677e1bdf03abbd0bef3.jpg"],"type":0,"id":9284680,"ga_prefix":"031317","title":"第一次去健身房，别被写满身体数据的体测表唬住了"},{"title":"人工智能又来抢人类的饭碗了，这次是插画师","ga_prefix":"031316","images":["http://pic3.zhimg.com/ae7d3c30accadbec5b7e6b561e025a6e.jpg"],"multipic":true,"type":0,"id":9284316},{"images":["http://pic2.zhimg.com/ad2650b0ccb51bc8c22850a393b363a1.jpg"],"type":0,"id":9284754,"ga_prefix":"031315","title":"武汉一城独大，湖北省会变成武汉省吗？"},{"images":["http://pic1.zhimg.com/43712bb32f8084e0b49cb3b8dd3ec378.jpg"],"type":0,"id":9284277,"ga_prefix":"031314","title":"咪蒙：网红，病人，潮水的一种方向"},{"images":["http://pic1.zhimg.com/9acf430f08c7a1c0386b11605c0888c0.jpg"],"type":0,"id":9281775,"ga_prefix":"031313","title":"留学在俄罗斯，发现俄罗斯人真的很爱喝酒"},{"images":["http://pic1.zhimg.com/9932ba8ec8416ecd946ca17d73e8040c.jpg"],"type":0,"id":9283915,"ga_prefix":"031312","title":"大误 · 遇到了郭靖黄蓉"},{"title":"对，那种白白滑滑的「椰果」和椰肉一点关系都没有","ga_prefix":"031311","images":["http://pic3.zhimg.com/46125292955a8ce387b933266b7e6526.jpg"],"multipic":true,"type":0,"id":9284211},{"images":["http://pic2.zhimg.com/7f8c86d621b84d6542919b27afacfd61.jpg"],"type":0,"id":9283401,"ga_prefix":"031310","title":"接下来我们会更经常看到「天舟」而不是「神舟」了"},{"images":["http://pic3.zhimg.com/2034f38b26720fe9e17356979328d81e.jpg"],"type":0,"id":9283350,"ga_prefix":"031309","title":"全球化为什么退潮了？"},{"images":["http://pic3.zhimg.com/0e23bae90973775490c0009284cfd41e.jpg"],"type":0,"id":9283128,"ga_prefix":"031308","title":"对着他人吐一口烟，和捡起一块板砖扔过去，性质一样"},{"images":["http://pic3.zhimg.com/d56b0356cdefee532f97f365d2860c66.jpg"],"type":0,"id":9283069,"ga_prefix":"031307","title":"后来我明白，混乱才是世界的常态，解决问题总是聊胜于无"},{"images":["http://pic2.zhimg.com/773ac2c6085bc0b58fb440e5e518fd39.jpg"],"type":0,"id":9283360,"ga_prefix":"031307","title":"油价低迷，沙特国家石油公司也要上市了"},{"images":["http://pic1.zhimg.com/6c9dba959c04c2bbbd0b42efa70c7f38.jpg"],"type":0,"id":9283348,"ga_prefix":"031307","title":"据说最近经济不景气，消费升级的项目却挺火，不矛盾吗？"},{"images":["http://pic1.zhimg.com/46ed09f1e9c78a9b5271dd809f903bf0.jpg"],"type":0,"id":9283336,"ga_prefix":"031306","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic3.zhimg.com/5b8e251d6567ece8e1a176119b786eda.jpg"]
         * type : 0
         * id : 9284733
         * ga_prefix : 031322
         * title : 小事 · 宝贝，宝贝，给你一点甜
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
