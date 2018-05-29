package com.example.ecshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.core.activity.ProxyActivity;
import com.example.core.configuration.ShopConfigure;
import com.example.core.delegate.ShopDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public ShopDelegate setDelegate() {
        return new TestDelegate();
    }
}
