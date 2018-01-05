package cn.lawwing.simplevideodemo.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class ScrollImageBean {

    private List<RollimagesBean> rollimages;

    public List<RollimagesBean> getRollimages() {
        return rollimages;
    }

    public void setRollimages(List<RollimagesBean> rollimages) {
        this.rollimages = rollimages;
    }

    public static class RollimagesBean {
        /**
         * title : image1
         * path : http://192.168.0.227:8888/test/Chrysanthemum.jpg
         */

        private String title;
        private String path;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
