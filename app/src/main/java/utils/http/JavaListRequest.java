package utils.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import utils.JsonUtil;

/**
 * Created by zhangke on 15/7/23.
 */
public class JavaListRequest<List> extends JsonRequest<List> {

    private Class mClass;

    private boolean filter;

    private static HandlePostBody handleBody;


    public JavaListRequest(int method, String url, String requestBody, Response.Listener<List> listener,
                          Response.ErrorListener errorListener, Class tClass, boolean filter) {
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
    protected Response<List> parseNetworkResponse(NetworkResponse response) {
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
            return Response.success((List) JsonUtil.jsonToListUnException(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        } catch (Exception e){
            return Response.error(new ParseError(e));
        }
    }

    public void doRequest(){
        complete = false;
        if(handleBody != null){
            setmRequestBody(handleBody.handleBody(getRequestBody()));
        }
        HttpManager.getInstance().doRequest(this);
    }

    public static void setHandleBody(HandlePostBody handleBody) {
        JavaListRequest.handleBody = handleBody;
    }

    public Class getItemClass() {
        return mClass;
    }
}
