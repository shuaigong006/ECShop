package com.example.ecshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.core.activity.ProxyActivity;
import com.example.core.delegate.ShopDelegate;
import com.example.ec.main.ECBottomDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public ShopDelegate setRootDelegate() {
        return new ECBottomDelegate();
    }
}
