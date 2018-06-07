package com.example.ec.main.personal.profile;

import android.content.DialogInterface;
import android.graphics.Camera;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.delegate.ShopDelegate;
import com.example.core.util.callback.CallbackManager;
import com.example.core.util.callback.CallbackType;
import com.example.core.util.callback.IGlobalCallback;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListEntity;
import com.example.ui.date.DateDialogUtil;

public class UserProfileClickListener extends SimpleClickListener {

    private final ShopDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "人妖"};

    public UserProfileClickListener(ShopDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListEntity entity = (ListEntity) adapter.getData().get(position);
        int id = entity.getId();
        switch (id) {
            case 1:
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(DELEGATE)
                                .load(args)
                                .into(avatar);
                    }
                });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(entity.getDelegate());
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                DateDialogUtil dialogUtil = new DateDialogUtil();
                dialogUtil.showDialog(DELEGATE.getContext());
                dialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(DELEGATE.getContext());
        dialog.setSingleChoiceItems(mGenders, 0, listener);
        dialog.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
