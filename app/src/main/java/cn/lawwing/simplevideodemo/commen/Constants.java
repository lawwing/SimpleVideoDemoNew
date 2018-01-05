package cn.lawwing.simplevideodemo.commen;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class Constants {
    /**
     * 服务器地址，默认资源是放在服务器根目录下的test文件夹
     */
    public static final String BASE_URL = "http://192.168.0.227:8888/test";
    /**
     * 默认的图片数据，对应数据格式
     *
     * @link ScrollImageBean
     */
    public static String image_json = "{\"rollimages\":[\n" +
            "{\"title\":\"image1\", \"path\":\"" + BASE_URL + "/Chrysanthemum.jpg\"},\n" +
            "{\"title\":\"image2\", \"path\":\"" + BASE_URL + "/Desert.jpg\"},\n" +
            "{\"title\":\"image3\", \"path\":\"" + BASE_URL + "/Hydrangeas.jpg\"},\n" +
            "{\"title\":\"image4\", \"path\":\"" + BASE_URL + "/Jellyfish.jpg\"},\n" +
            "{\"title\":\"image5\", \"path\":\"" + BASE_URL + "/Koala.jpg\"},\n" +
            "{\"title\":\"image6\", \"path\":\"" + BASE_URL + "/Lighthouse.jpg\"},\n" +
            "{\"title\":\"image7\", \"path\":\"" + BASE_URL + "/Penguins.jpg\"},\n" +
            "{\"title\":\"image8\", \"path\":\"" + BASE_URL + "/Tulips.jpg\"}\n" +
            "]}";
}
