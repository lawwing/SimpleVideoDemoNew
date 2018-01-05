package cn.lawwing.simplevideodemo.utils;

import java.util.Random;


/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class RandomUtils {
    /**
     * 生成随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
