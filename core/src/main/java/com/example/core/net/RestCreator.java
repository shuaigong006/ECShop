package com.example.core.net;

import com.example.core.configuration.ConfigureType;
import com.example.core.configuration.ShopConfigure;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//配置网络
public class RestCreator {

    //构建全局Retrofit客户端
    private static final class RetrofitHolder {
        private static final String BASE_URL = ShopConfigure.getConfiguration(ConfigureType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //构建OkHttp
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 10;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    //Service接口
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }

    //获取Service接口
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
}
