package com.example.ec.main.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURES = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, JSONObject data) {
        super(fm);
        //获取tabs信息,注意,这里的tabs是一条信息
        final JSONArray tabs = data.getJSONArray("tabs");
        final int size = tabs.size();
        for (int i = 0; i < size; i++) {
            final JSONObject eachTab = tabs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();

            for (int j = 0,pictureSize = pictureUrls.size(); j < pictureSize; j++) {
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLES.add(name);
            PICTURES.add(eachTabPicturesArray);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ImageDelegate.create(PICTURES.get(0));
        } else {
            return ImageDelegate.create(PICTURES.get(1));
        }
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
