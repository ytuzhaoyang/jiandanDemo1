package utils.http;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.JsonSyntaxException;
import com.sina.young.jiandandemo1.base.BaseApplication;

import org.json.JSONException;

/**
 * Created by zhangke on 15/9/9.
 */
public class ErrorHandler {

    public static long onError(long lastError, VolleyError error){
        if (System.currentTimeMillis() - lastError > 200) {
            lastError = System.currentTimeMillis();
            String err = "";
            if(error instanceof RequestFail){
                Toast.makeText(BaseApplication.getInstance(), ((RequestFail) error).getDesc(), Toast.LENGTH_SHORT).show();
                return lastError;
            }
            if(error == null || error.networkResponse == null){
                err = "网络异常,请检查网络设置";
                if(error != null){
                    if(error.getCause() instanceof JSONException || error.getCause() instanceof JsonSyntaxException){
                        Log.d("request", error.getMessage() + "======" + error.getCause().toString());
                        err = "错误的数据格式,请联系管理员";
                    }
                }
                Toast.makeText(BaseApplication.getInstance(), err, Toast.LENGTH_SHORT).show();
                return lastError;
            }
            switch (error.networkResponse.statusCode){
                case 404:
                    err += "网络异常,请检查网络设置";
                    break;
                case 500:
                    err += "服务器异常,请联系管理员";
                    break;
                case 504:
                    err += "连接超时,请重试";
                    break;
                default:
                    err += "其它错误,请联系管理员";
                    break;
            }
            Toast.makeText(BaseApplication.getInstance(), err + " 错误号：" + error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
        }
        return lastError;
    }

    /**
     * 打印哪个URL报错-专门针对秒车APP
     * @param lastError
     * @param error
     * @param url
     * @return
     */
    public static long onError(long lastError, VolleyError error, String url){
        if (System.currentTimeMillis() - lastError > 200) {
            lastError = System.currentTimeMillis();
            String err = "";
            if(error instanceof RequestFail){
                Toast.makeText(BaseApplication.getInstance(), ((RequestFail) error).getDesc(), Toast.LENGTH_SHORT).show();
                return lastError;
            }
            if(error == null || error.networkResponse == null){
                err = "网络异常,请检查网络设置";
                if(error != null){
                    if(error.getCause() instanceof JSONException || error.getCause() instanceof JsonSyntaxException){
                        Log.d("request-错误的数据格式--", url);
//                        err = "错误的数据格式,请联系管理员";
                        err = "哎呦~出了点小问题，一会再试试吧！";
                    }
                }
                Toast.makeText(BaseApplication.getInstance(), err, Toast.LENGTH_SHORT).show();
                return lastError;
            }
            switch (error.networkResponse.statusCode){
                case 404:
                    err += "网络异常,请检查网络设置";
                    break;
                case 500:
//                    err += "服务器异常,请联系管理员";
                    err += "哎呦~~出了点小问题，一会再试试吧！";
                    break;
                case 504:
                    err += "连接超时,请重试";
                    break;
                default:
//                    err += "其它错误,请联系管理员";
                    err += "哎呦~~~出了点小问题，一会再试试吧！";
                    break;
            }
            Toast.makeText(BaseApplication.getInstance(), err + " 错误号：" + error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
        }
        return lastError;
    }
}
