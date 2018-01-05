package cn.lawwing.simplevideodemo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/4 0004.
 */
public class ShowTextBean implements Serializable {

    private List<RollmsgBean> rollmsg;

    public List<RollmsgBean> getRollmsg() {
        return rollmsg;
    }

    public void setRollmsg(List<RollmsgBean> rollmsg) {
        this.rollmsg = rollmsg;
    }

    public static class RollmsgBean {
        /**
         * title : abc
         * text : ccc-aaaaaaaaaa最高人民检察院检察长曹建明报告了全国检察机关2015年主要工作和2016年工作安排。
         */

        private String title;
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
