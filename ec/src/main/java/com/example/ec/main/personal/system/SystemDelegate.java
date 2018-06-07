package com.example.ec.main.personal.system;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;

import com.example.core.delegate.ShopDelegate;
import com.example.core.util.callback.CallbackManager;
import com.example.core.util.callback.CallbackType;
import com.example.ec.R;
import com.example.ec.R2;
import com.example.ec.main.personal.address.AddressDelegate;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListEntity;
import com.example.ec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SystemDelegate extends ShopDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        ListEntity push = new ListEntity.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
//                .setDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH)
                                    .executeCallback(null);
                        }else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).
                                    executeCallback(null);
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListEntity about = new ListEntity.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final List<ListEntity> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
