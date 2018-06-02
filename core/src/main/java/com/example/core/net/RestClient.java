package com.example.core.net;

import com.example.core.net.callback.IFailure;
import com.example.core.net.callback.ISuccess;
import com.example.core.net.callback.RequestCallback;

import java.util.WeakHashMap;

import retrofit2.Call;

//网络的对外方法(构造者模式)
public class RestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;

    RestClient(String url, WeakHashMap<String, Object> params, ISuccess success,
               IFailure failure) {
        this.URL = url;
        this.PARAMS = params;
        this.SUCCESS = success;
        this.FAILURE = failure;
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
        }
        if (call != null) {
            call.enqueue(new RequestCallback(SUCCESS,FAILURE));
        }
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public static class RestClientBuilder {
        private String mUrl;
        private WeakHashMap<String, Object> mParams;
        private ISuccess mSuccess;
        private IFailure mFailure;

        private RestClientBuilder() {
            if (mParams == null) {
                mParams = new WeakHashMap<>();
            }
        }

        public final RestClientBuilder url(String url) {
            this.mUrl = url;
            return this;
        }

        public final RestClientBuilder params(String key, Object value) {
            mParams.put(key, value);
            return this;
        }

        public final RestClientBuilder params(WeakHashMap<String, Object> params) {
            mParams.putAll(params);
            return this;
        }

        public final RestClientBuilder success(ISuccess success){
            this.mSuccess = success;
            return this;
        }

        public final RestClientBuilder failure(IFailure failure){
            this.mFailure = failure;
            return this;
        }

        public final RestClient build() {
            return new RestClient(mUrl, mParams,mSuccess,mFailure);
        }
    }
}
