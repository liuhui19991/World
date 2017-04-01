package com.liuhui.world.ui.model;

import java.util.List;

/**
 * Created by liuhui on 2017/3/31.
 */

public class DetailZhiHuModel extends DataModel {

    /**
     * image_source : 《帕特森》
     * title : 谁说普通人的生活就不能精彩有趣呢？
     * image : http://pic4.zhimg.com/e39083107b7324c6dbb725da83b1d7fb.jpg
     * share_url : http://daily.zhihu.com/story/9165434
     * js : []
     * ga_prefix : 012121
     * section : {"thumbnail":"https://pic4.zhimg.com/v2-12d11924a71a2942133f8278122ab9db.jpg","id":28,"name":"放映机"}
     * images : ["http://pic1.zhimg.com/ffcca2b2853f2af791310e6a6d694e80.jpg"]
     * type : 0
     * id : 9165434
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private SectionBean section;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public SectionBean getSection() {
        return section;
    }

    public void setSection(SectionBean section) {
        this.section = section;
    }

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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public static class SectionBean {
        /**
         * thumbnail : https://pic4.zhimg.com/v2-12d11924a71a2942133f8278122ab9db.jpg
         * id : 28
         * name : 放映机
         */

        private String thumbnail;
        private int id;
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
