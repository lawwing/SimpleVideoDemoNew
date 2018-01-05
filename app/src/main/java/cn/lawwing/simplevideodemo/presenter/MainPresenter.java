package cn.lawwing.simplevideodemo.presenter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public interface MainPresenter {
    /**
     * 初始化现在的时间以及启动
     */
    void initNowTimeArea();

    /**
     * 联网获取滚动文字集合
     */
    void getInternetText();

    /**
     * 初始化底部文字
     *
     * @param response
     * @throws IOException
     */
    void initBottomShowText(Response response) throws IOException;

    /**
     * 获取文字失败的操作
     *
     * @param call
     * @param e
     */
    void onTextFaild(Call call, IOException e);

    /**
     * 初始化图片幻灯片区
     */
    void initImageView();

    /**
     * 更换图片
     */
    void changeImageView();
}
