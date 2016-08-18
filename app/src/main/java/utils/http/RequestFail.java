package utils.http;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * Created by zhangke on 16/1/26.
 */
public class RequestFail extends VolleyError {
    private String desc;

    public RequestFail(String desc) {
        this.desc = desc;
    }

    public RequestFail(NetworkResponse networkResponse, String desc) {
        super(networkResponse);
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
