package com.example.core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class RequestCallback implements Callback<String> {

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;

    public RequestCallback(ISuccess success, IFailure failure) {
        this.SUCCESS = success;
        this.FAILURE = failure;
    }

    //网络请求成功后将请求到的数据传入网络请求成功接口
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (call.isExecuted()) {
            if (response.isSuccessful()) {
                if (SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }
    }

    //网络请求失败后将请求到的数据传入网络请求失败接口
    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null){
            FAILURE.onFailure(t);
        }
    }
}
