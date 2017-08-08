package com.wangzai.latte.net;

import android.content.Context;

import com.wangzai.latte.net.callback.IError;
import com.wangzai.latte.net.callback.IFailure;
import com.wangzai.latte.net.callback.IRequest;
import com.wangzai.latte.net.callback.ISuccess;
import com.wangzai.latte.net.callback.RequestCallBack;
import com.wangzai.latte.net.download.DownloadHandler;
import com.wangzai.latte.ui.loader.LatteLoader;
import com.wangzai.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by wangzai on 2017/8/2.
 */

public class RestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle,
                      File file, String downloadDir, String extension, String name) {
        URL = url;
        CONTEXT = context;
        LOADER_STYLE = loaderStyle;
        BODY = body;
        DOWNLOAD_DIR = downloadDir;
        EXTENSION = extension;
        NAME = name;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        FILE = file;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(String httpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (CONTEXT != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (httpMethod) {
            case HttpMethod.GET:
                call = service.get(URL, PARAMS);
                break;
            case HttpMethod.POST:
                call = service.post(URL, PARAMS);
                break;
            case HttpMethod.POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case HttpMethod.PUT:
                call = service.put(URL, PARAMS);
                break;
            case HttpMethod.PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case HttpMethod.DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case HttpMethod.UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        call.enqueue(getRequestCallBack());
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBack(REQUEST, SUCCESS, FAILURE, ERROR, CONTEXT);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(
                URL,
                REQUEST,
                DOWNLOAD_DIR ,
                EXTENSION,
                NAME,
                SUCCESS,
                FAILURE,
                ERROR
        ).handlerDownload();
    }
}
