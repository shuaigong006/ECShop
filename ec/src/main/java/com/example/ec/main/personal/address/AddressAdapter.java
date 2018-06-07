package com.example.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.R;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;
import com.example.ui.recycler.MultipleRecyclerAdapter;

import java.util.List;

public class AddressAdapter extends MultipleRecyclerAdapter {

    public AddressAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultipleItem item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:
                final String name = item.getItem(MultipleFields.NAME);
                final String phone = item.getItem(AddressItemFields.PHONE);
                final String address = item.getItem(AddressItemFields.ADDRESS);
                final boolean isDefault = item.getItem(MultipleFields.TAG);
                final int id = item.getItem(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        RestClient.builder()
//                                .url("address.php")
//                                .params("id", id)
//                                .success(new ISuccess() {
//                                    @Override
//                                    public void onSuccess(String response) {
                        remove(holder.getLayoutPosition());
//                                    }
//                                })
//                                .build()
//                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
