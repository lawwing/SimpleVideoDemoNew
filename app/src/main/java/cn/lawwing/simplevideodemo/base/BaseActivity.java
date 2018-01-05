package cn.lawwing.simplevideodemo.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
