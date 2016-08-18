package utils.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import utils.JsonUtil;

/**
 * Created by zhangke on 15/7/23.
 */
public class JavaObjRequest<T> extends JsonRequest<T> {

    private Class<T> mClass;

    private boolean filter;

    private static HandlePostBody handleBody;

    public JavaObjRequest(String url, String requestBody, Response.Listener<T> listener,
                          Response.ErrorListener errorListener, Class<T> tClass) {
        this(Method.DEPRECATED_GET_OR_POST, url, requestBody, listener,errorListener,tClass, false);
    }

    public JavaObjRequest(String url, String requestBody, Response.Listener<T> listener,
                          Response.ErrorListener errorListener, Class<T> tClass, boolean filter) {
        this(Method.DEPRECATED_GET_OR_POST, url, requestBody, listener, errorListener, tClass, filter);
    }

    public JavaObjRequest(int method, String url, String requestBody, Response.Listener<T> listener,
                       Response.ErrorListener errorListener, Class<T> tClass) {
        this(method, url, requestBody, listener, errorListener, tClass, false);
}

    public JavaObjRequest(int method, String url, String requestBody, Response.Listener<T> listener,
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
            Log.d("Request", jsonString);

             if(deliverPreHandler != null){
                deliverPreHandler.preHandler(jsonString);
            }
            if(filter) {
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
            return Response.success(JsonUtil.jsonObjToJavaUnException(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        } catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        } catch (Exception e){
            return Response.error(new ParseError(e));
        }
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public void doRequest(){
        complete = false;
        if(handleBody != null){
            setmRequestBody(handleBody.handleBody(getRequestBody()));
        }
        HttpManager.getInstance().doRequest(this);
    }

    public static void setHandleBody(HandlePostBody handleBody) {
        JavaObjRequest.handleBody = handleBody;
    }
}
