package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zhaoyang on 16/8/15.
 *
 * 网络连接工具类,用于判断当前网络状态以及是否为WI-FI连接
 */
public class NetWorkUtils {

    /**
     * 判断网络是否链接
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * 判断当前是否为WI-FI连接
     * @param context
     * @return
     */
    public static boolean getNetWorkStyle(Context context) {
       ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //获取网络链接类型
        int type = networkInfo.getType();
        //获取网络链接类型名
        String typeName = networkInfo.getTypeName();
        //获取连接状态
        NetworkInfo.State state = networkInfo.getState();
        return state == NetworkInfo.State.CONNECTED && type == ConnectivityManager.TYPE_WIFI;
    }
}
