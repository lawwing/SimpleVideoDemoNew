package cn.lawwing.simplevideodemo.network;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class OkhttpUtils {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }
}
