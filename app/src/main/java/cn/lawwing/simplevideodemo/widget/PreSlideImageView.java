package cn.lawwing.simplevideodemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import cn.lawwing.simplevideodemo.R;
import cn.lawwing.simplevideodemo.utils.GlideUtils;

/**
 * Created by Administrator on 2018/1/5 0005.
 * <p>
 * 幻灯片封装控件
 */

public class PreSlideImageView extends RelativeLayout {
    /**
     * 是否正在播放幻灯片
     */
    private boolean isPlaying = false;
    private ImageView imageView1;
    private ImageView imageView2;
    private int imagePoi = 0;
    private ArrayList<String> imageUrls = new ArrayList<>();
    private boolean isView1 = true;

    public PreSlideImageView(Context context) {
        super(context);
        initLayout(context);
    }

    public PreSlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public PreSlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void initLayout(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.pre_slide_img_layout, this);
        imageView1 = (ImageView) layout.findViewById(R.id.imageView1);
        imageView2 = (ImageView) layout.findViewById(R.id.imageView2);
    }

    public void playImages(ArrayList<String> imageUrls) {
        isPlaying = true;
        this.imageUrls = imageUrls;
        GlideUtils.loadImage(imageUrls.get(0), R.mipmap.ic_launcher, imageView1);
        GlideUtils.loadImage(imageUrls.get(1), R.mipmap.ic_launcher, imageView2);
        imageView1.setVisibility(VISIBLE);
        imageView2.setVisibility(GONE);
        imagePoi = 1;
        isView1 = true;
    }

    public void stopPlayImages() {
        isPlaying = false;
    }

    public void changeImage() {
        //改变要加载图片的下标
        if (imagePoi < imageUrls.size() - 1) {
            imagePoi++;
        } else {
            imagePoi = 0;
        }
        if (isView1) {
            //如果iv1显示,改变图片的时候，就吧iv1隐藏，iv2显示出来，并且加载下一张图片到iv1
            imageView1.setVisibility(GONE);
            imageView2.setVisibility(VISIBLE);
            GlideUtils.loadImage(imageUrls.get(imagePoi), R.mipmap.ic_launcher, imageView1);
        } else {
            //如果iv1隐藏,改变图片的时候，就吧iv2隐藏，iv1显示出来，并且加载下一张图片到iv2
            imageView1.setVisibility(VISIBLE);
            imageView2.setVisibility(GONE);
            GlideUtils.loadImage(imageUrls.get(imagePoi), R.mipmap.ic_launcher, imageView2);
        }
        isView1 = !isView1;

    }
}
