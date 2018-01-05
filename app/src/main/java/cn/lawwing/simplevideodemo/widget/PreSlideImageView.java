package cn.lawwing.simplevideodemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/1/5 0005.
 * <p>
 * 幻灯片封装控件
 */

public class PreSlideImageView extends RelativeLayout {

    public PreSlideImageView(Context context) {
        super(context);
    }

    public PreSlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PreSlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
}
