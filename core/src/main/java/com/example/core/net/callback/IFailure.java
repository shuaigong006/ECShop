package com.example.core.net.callback;

//网络请求失败接口
public interface IFailure {

    void onFailure(Throwable t);
}
