package com.example.ec.main.personal.system;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;

import com.example.core.delegate.ShopDelegate;
import com.example.ec.R;
import com.example.ec.R2;
import com.example.ui.date.DateDialogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class NameDelegate extends ShopDelegate {

    @BindView(R2.id.btn_name)
    AppCompatEditText mName = null;

    @OnClick(R2.id.btn_name_submit)
    void onClickSubmit(){
        if (!mName.getText().toString().isEmpty()){
           //存储到数据库中,更新服务器端数据
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }
}
