package com.example.core.delegate.bottom;

public class BottomItemBean {

    //分页的字体图标
    private final CharSequence ICON;
    //分页的标题
    private final CharSequence TITLE;

    public BottomItemBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
