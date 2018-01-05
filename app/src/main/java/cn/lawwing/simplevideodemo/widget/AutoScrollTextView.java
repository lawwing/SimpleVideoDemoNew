package cn.lawwing.simplevideodemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;

import cn.lawwing.simplevideodemo.R;
import cn.lawwing.simplevideodemo.utils.RandomUtils;

/**
 * 自动滚动文字控件
 * Created by Administrator on 2018/1/4 0004.
 */

public class AutoScrollTextView extends AppCompatTextView {
    /**
     * 文字长度
     */
    private float textLength = 0f;
    /**
     * 滚动条长度
     */
    private float viewWidth = 0f;
    /**
     * 文本x轴 的坐标
     */
    private float tx = 0f;
    /**
     * 文本Y轴的坐标
     */
    private float ty = 0f;
    /**
     * 文本当前长度
     */
    private float temp_tx1 = 0.0f;
    /**
     * 文本当前变换的长度
     */
    private float temp_tx2 = 0x0f;
    /**
     * 文本滚动开关
     */
    private boolean isStarting = false;
    /**
     * 画笔对象
     */
    private Paint paint = null;
    /**
     * 显示的文字
     */
    private String text = "";
    /**
     * 滚动速度，默认是1.0，数值越大速度越大，speed大于0.1
     */
    private double speed = 1.0;
    private ArrayList<String> textDatas = new ArrayList<>();
    private WindowManager windowManager;

    public AutoScrollTextView(Context context) {
        super(context);
    }

    public AutoScrollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化自动滚动条,每次改变文字内容时，都需要重新初始化一次
     *
     * @param windowManager 获取屏幕
     * @param text          显示的内容
     * @param speed         滚动速度，默认是1.0，数值越大速度越大，speed大于0.1
     */
    public void initScrollTextView(WindowManager windowManager, ArrayList<String> textDatas, double speed) {
        this.speed = speed;
        // 得到画笔,获取父类的textPaint
        paint = this.getPaint();
        // 得到文字
        this.textDatas = textDatas;
        this.windowManager = windowManager;

        text = textDatas.get(RandomUtils.getRandom(0, textDatas.size() - 1));

        textLength = paint.measureText(text);// 获得当前文本字符串长度
        viewWidth = this.getWidth();// 获取宽度return mRight - mLeft;
        if (viewWidth == 0) {
            if (windowManager != null) {
                // 获取当前屏幕的属性
                Display display = windowManager.getDefaultDisplay();
                viewWidth = display.getWidth();// 获取屏幕宽度

            }
        }
        tx = textLength;
        temp_tx1 = viewWidth + textLength;
        temp_tx2 = viewWidth + textLength * 2;// 自己定义，文字变化多少
        // 文字的大小+距顶部的距离
        ty = this.getTextSize() + this.getPaddingTop();
    }

    /**
     * 开始滚动的方法
     */
    public void starScroll() {
        // 开始滚动
        isStarting = true;
        this.invalidate();// 刷新屏幕
    }

    /**
     * 停止滚动的方法
     */
    public void stopScroll() {
        // 停止滚动
        isStarting = false;
        this.invalidate();// 刷新屏幕
    }

    /**
     * 重写onDraw方法
     * <p>
     * 调用invalidate方法则会进入此方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 如果已经开始(isStarting为true)，则无限执行下面方法，达到文字滚动的效果
         */
        if (isStarting) {
            paint.setColor(getResources().getColor(R.color.bottom_show_textcolor));
            paint.setAntiAlias(true);
            canvas.drawText(text, temp_tx1 - tx, ty, paint);
            tx += speed;
            //一行文字滚动完之后进入下面方法
            if (tx >= temp_tx2) {
                // 把文字设置到最右边开始
                tx = temp_tx1 - viewWidth;
                if (windowManager != null) {
                    initScrollTextView(windowManager, textDatas, speed);
                    Log.e("lawwing", "这个是我的");
                }
            }
            this.invalidate();// 刷新屏幕
        }
        super.onDraw(canvas);
    }
}
