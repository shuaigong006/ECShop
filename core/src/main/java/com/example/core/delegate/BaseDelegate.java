package com.example.core.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

//delegate基础类
public abstract class BaseDelegate extends SupportFragment {

    public abstract Object setLayout();

    private Unbinder mUnBinder = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        //判断布局的类型
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("view must be view or integer");
        }
        mUnBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
