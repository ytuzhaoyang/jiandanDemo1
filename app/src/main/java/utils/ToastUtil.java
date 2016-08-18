package utils;

import android.widget.Toast;

import com.sina.young.jiandandemo1.base.BaseApplication;


/**
 * Created by zhangke on 15/8/3.
 */
public class ToastUtil {
    private static Toast mToast ;

    public static void show(String s){
        if(mToast==null) {
            mToast = Toast.makeText(BaseApplication.getInstance(), s, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(s);
        }

        mToast.show();
    }
}
