package com.example.ecshop;

import android.app.Application;

import com.example.core.configuration.ShopConfigure;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShopConfigure.init()
                .withApiHost("")
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
