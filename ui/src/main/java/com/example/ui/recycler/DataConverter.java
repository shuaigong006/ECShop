package com.example.ui.recycler;

import java.util.ArrayList;

public abstract class DataConverter {

    protected final ArrayList<MultipleItem> ITEMS = new ArrayList<>();
    public abstract ArrayList<MultipleItem> convert();

    private String mJson = null;

    protected final String getJson() {
        if (mJson == null){
            throw new NullPointerException("Data is null");
        }
        return mJson;
    }

    public final DataConverter setJson(String mJson) {
        this.mJson = mJson;
        return this;
    }
}
