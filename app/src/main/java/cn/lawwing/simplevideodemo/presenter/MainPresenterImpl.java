package cn.lawwing.simplevideodemo.presenter;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import cn.lawwing.simplevideodemo.beans.ScrollImageBean;
import cn.lawwing.simplevideodemo.beans.ShowTextBean;
import cn.lawwing.simplevideodemo.utils.RandomUtils;
import cn.lawwing.simplevideodemo.view.MainView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private Context mContext;

    public MainPresenterImpl(Context context, MainView mainView) {
        this.mainView = mainView;
        mContext = context;
    }

    @Override
    public void initNowTimeArea() {
        mainView.initTimeView(mainView.getTimeFormatString());
    }

    @Override
    public void getInternetText() {
        mainView.getInternetText();
    }

    @Override
    public void initBottomShowText(Response response) throws IOException {
        Gson gson = new Gson();
        ShowTextBean showTextBean = gson.fromJson(response.body().string(), ShowTextBean.class);
        mainView.initBottomView(showTextBean);
    }


    @Override
    public void onTextFaild(Call call, IOException e) {
    }

    @Override
    public void initImageView() {
        mainView.initImageView(mainView.getImageData());
    }

    @Override
    public void changeImageView() {
        List<ScrollImageBean.RollimagesBean> rollimagesBeens = mainView.getImageData().getRollimages();
        mainView.changeImageView(rollimagesBeens.get(RandomUtils.getRandom(0, rollimagesBeens.size() - 1)).getPath());
    }

    @Override
    public void initVideoView() {
        mainView.preVideoView(mainView.getVideoPath());
    }

    @Override
    public void playVideo() {
        mainView.playVideo(mainView.getVideoPath());
    }

    @Override
    public void stopVideo() {
        mainView.stopVideo();
    }

    @Override
    public void pauseVideo() {
        mainView.pauseVideo();
    }
}
