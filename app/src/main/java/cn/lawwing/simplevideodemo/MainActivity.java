package cn.lawwing.simplevideodemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lawwing.simplevideodemo.base.BaseActivity;
import cn.lawwing.simplevideodemo.beans.ScrollImageBean;
import cn.lawwing.simplevideodemo.beans.ShowTextBean;
import cn.lawwing.simplevideodemo.commen.Constants;
import cn.lawwing.simplevideodemo.network.OkhttpUtils;
import cn.lawwing.simplevideodemo.presenter.MainPresenter;
import cn.lawwing.simplevideodemo.presenter.MainPresenterImpl;
import cn.lawwing.simplevideodemo.utils.TimeUtils;
import cn.lawwing.simplevideodemo.view.MainView;
import cn.lawwing.simplevideodemo.widget.AutoScrollTextView;
import cn.lawwing.simplevideodemo.widget.PreSlideImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements MainView, MediaPlayer.OnCompletionListener {
    @BindView(R.id.cm_now_time)
    Chronometer mNowTime;
    @BindView(R.id.tv_bottom_showtext)
    AutoScrollTextView mShowText;
    @BindView(R.id.iv_showimage)
    PreSlideImageView mShowImage;
    @BindView(R.id.vv_showvideo)
    VideoView mShowVideo;
    @BindView(R.id.rl_showvideo)
    RelativeLayout mShowVideoLayout;
    @BindView(R.id.iv_playvideo)
    ImageView mPlayVideoImageBtn;
    Unbinder unbinder;

    private MainPresenter mPresenter;
    private int i = 0;
    //更改图片的时间
    private static final int CHANGE_IMAGE_TIME = 5;

    private boolean isFinishvideo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl(this, this);
        mPresenter.initNowTimeArea();
        mPresenter.getInternetText();
        mPresenter.initImageView();
        mPresenter.initVideoView();
        //使视频循环播放,一进去就播放视频
        mPresenter.playVideo();
    }


    @Override
    protected void onDestroy() {
        //释放资源
        unbinder.unbind();
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public String getTimeFormatString() {
        return "yyyy年MM月dd日 EEEE HH:mm:ss";
    }

    @Override
    public void initTimeView(final String timeFormatString) {
        mNowTime.setText(TimeUtils.getNowTime(timeFormatString));
        mNowTime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                mNowTime.setText(TimeUtils.getNowTime(timeFormatString));
                //添加计数器，每CHANGE_IMAGE_TIME秒
                i++;
                if (i == CHANGE_IMAGE_TIME) {
                    i = 0;
                    mPresenter.changeImageView();
                }
            }
        });
        mNowTime.start();
    }

    @Override
    public void getInternetText() {
        OkhttpUtils.enqueue(new Request.Builder().url(Constants.BASE_URL + "/roll_msg.txt").build(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mPresenter.onTextFaild(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mPresenter.initBottomShowText(response);
            }
        });
    }

    @Override
    public void initBottomView(ShowTextBean showTextBean) {
        ArrayList<String> textDatas = new ArrayList<>();
        for (ShowTextBean.RollmsgBean bean : showTextBean.getRollmsg()) {
            if (bean != null) {
                textDatas.add(bean.getText());
            }
        }
        mShowText.initScrollTextView(MainActivity.this.getWindowManager(), textDatas, 6);
        mShowText.starScroll();
    }

    @Override
    public ScrollImageBean getImageData() {
        Gson gson = new Gson();
        ScrollImageBean scrollImageBean = gson.fromJson(Constants.image_json, ScrollImageBean.class);
        return scrollImageBean;
    }

    @Override
    public void initImageView(ScrollImageBean imgDatas) {
        ArrayList<String> imageUrls = new ArrayList<>();
        for (ScrollImageBean.RollimagesBean bean : imgDatas.getRollimages()) {
            imageUrls.add(bean.getPath());
        }
        mShowImage.playImages(imageUrls);
    }

    @Override
    public void changeImageView(String path) {
        if (mShowImage.isPlaying()) {
            mShowImage.changeImage();
        }
    }

    @Override
    public String getVideoPath() {
        return Constants.BASE_URL + "/sobigmp4vi_sa7nyhd5.mp4";
    }

    @Override
    public void preVideoView(String videoPath) {
        mShowVideo.setVideoPath(videoPath);
        mShowVideo.setOnCompletionListener(this);
        mShowVideo.setMediaController(new MediaController(this));
    }

    @Override
    public void playVideo(String videoPath) {
        mPlayVideoImageBtn.setVisibility(View.GONE);
        if (isFinishvideo) {
            mShowVideo.setVideoPath(videoPath);
        }
        mShowVideo.start();
        isFinishvideo = false;
    }

    @Override
    public void stopVideo() {
        mPlayVideoImageBtn.setVisibility(View.VISIBLE);
        mShowVideo.stopPlayback();
    }

    @Override
    public void pauseVideo() {
        // mPlayVideoImageBtn.setVisibility(View.VISIBLE);
        mShowVideo.pause();
    }

    @OnClick({R.id.rl_showvideo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_showvideo:
                //点击视频框
                if (mShowVideo != null) {
                    if (!mShowVideo.isPlaying()) {
                        //此处是未播放的状态
                        mPresenter.playVideo();
                    } else {
                        mPresenter.pauseVideo();
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        isFinishvideo = true;
        mPresenter.stopVideo();
        mPresenter.playVideo();
    }
}
