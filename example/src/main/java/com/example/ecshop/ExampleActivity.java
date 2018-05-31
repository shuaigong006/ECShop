package com.example.ecshop;

import com.example.core.activity.ProxyActivity;
import com.example.core.delegate.ShopDelegate;
import com.example.ec.main.ECBottomDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public ShopDelegate setRootDelegate() {
        return new ECBottomDelegate();
    }
}
