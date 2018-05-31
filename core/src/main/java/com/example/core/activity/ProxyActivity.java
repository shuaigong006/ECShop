package com.example.core.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.example.core.R;
import com.example.core.delegate.ShopDelegate;

import me.yokeyword.fragmentation.SupportActivity;

//基础activity类
public abstract class ProxyActivity extends SupportActivity {

    public abstract ShopDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    //初始化容器
    private void initContainer(@Nullable Bundle savedInstanceState) {
        FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        //离开当前Activity等情况下,系统会调用onSaveInstanceState()帮你保存当前Activity的状态,数据
        //如果不判断会导致fragment重叠
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    //退出时回收垃圾
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收机制
        System.gc();
        System.runFinalization();
    }
}
