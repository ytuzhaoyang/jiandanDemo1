package utils.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhaoyang on 16/6/15.
 */
public class GsonRequest<T> extends JsonRequest<T> {

    private  Response.Listener<T> mListener;

    private Gson mGson;

    private Class<T> mClass;

    private boolean filter;

    private static HandlePostBody handleBody;

    public GsonRequest(String url, String requestBody, Response.Listener<T> listener,
                          Response.ErrorListener errorListener, Class<T> tClass) {
        this(Method.DEPRECATED_GET_OR_POST, url, requestBody, listener,errorListener,tClass, false);
    }

    public GsonRequest(String url, String requestBody, Response.Listener<T> listener,
                          Response.ErrorListener errorListener, Class<T> tClass, boolean filter) {
        this(Method.DEPRECATED_GET_OR_POST, url, requestBody, listener, errorListener, tClass, filter);
    }

    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener,
                          Response.ErrorListener errorListener, Class<T> tClass) {
        this(method, url, requestBody, listener, errorListener, tClass, false);
    }

    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener,
                          Response.ErrorListener errorListener, Class<T> tClass, boolean filter) {
        super(method, url, requestBody, listener,errorListener);
        this.mClass = tClass;
        this.filter = filter;
        setDefaultErrorHandler(new DefaultErrorHandler() {
            @Override
            public void onError(VolleyError error) {
                lastError = ErrorHandler.onError(lastError, error);
            }
        });
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.d("Request", "parseNetworkResponse: -->"+jsonString);
            if (deliverPreHandler != null){
                deliverPreHandler.preHandler(jsonString);
            }
            if (filter){
                JSONObject jso = new JSONObject(jsonString);
                if(0 != Integer.parseInt(jso.get("code").toString())){
                    return Response.error(new RequestFail(response, jso.getString("desc")));
                }

                if(!jso.has("content")){
                    return Response.success(null,
                            HttpHeaderParser.parseCacheHeaders(response));
                }
                jsonString = jso.getString("content");
            }
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
