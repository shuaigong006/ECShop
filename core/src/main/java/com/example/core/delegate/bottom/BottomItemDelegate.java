package com.example.core.delegate.bottom;

import android.widget.Toast;

import com.example.core.delegate.ShopDelegate;

public abstract class BottomItemDelegate extends ShopDelegate {

    private static final int WAIT_TIME = 2000;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
            if (TOUCH_TIME != 0) {
                TOUCH_TIME = 0;
            }
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
