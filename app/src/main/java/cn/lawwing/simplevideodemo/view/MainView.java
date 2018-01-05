package cn.lawwing.simplevideodemo.view;

import cn.lawwing.simplevideodemo.beans.ScrollImageBean;
import cn.lawwing.simplevideodemo.beans.ShowTextBean;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public interface MainView {
    /**
     * 获取当前的时间
     *
     * @return
     */
    String getTimeFormatString();

    /**
     * 初始化当前时间的VIEW
     *
     * @param timeFormatString
     */
    void initTimeView(String timeFormatString);

    /**
     * 从网络获取数据
     */
    void getInternetText();

    /**
     * 初始化底部字
     *
     * @param resultBean
     */
    void initBottomView(ShowTextBean resultBean);

    /**
     * 获取图片数组
     */
    ScrollImageBean getImageData();

    /**
     * 初始化幻灯片区
     *
     * @param imgDatas
     */
    void initImageView(ScrollImageBean imgDatas);

    /**
     * 改变图片
     *
     * @param path
     */
    void changeImageView(String path);

    /**
     * 获取视频的路劲
     */
    String getVideoPath();

    /**
     * 预加载视频
     *
     * @param videoPath
     */
    void preVideoView(String videoPath);

    /**
     * 播放视频
     *
     * @param videoPath
     */
    void playVideo(String videoPath);

    /**
     * 停止播放
     */
    void stopVideo();

    /**
     * 暂停播放
     */
    void pauseVideo();
}
