package cn.lawwing.simplevideodemo.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.lawwing.simplevideodemo.base.BaseApp;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class GlideUtils {
    public static void loadImage(String path, int defaultImage, ImageView imageView) {
        Glide.with(BaseApp.getContext()).load(path).error(defaultImage).placeholder(defaultImage).into(imageView);
    }
}
