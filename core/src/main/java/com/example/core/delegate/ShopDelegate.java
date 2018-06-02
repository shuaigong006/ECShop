package com.example.core.delegate;

//对外delegate类
public abstract class ShopDelegate extends PermissionCheckDelegate{

    @SuppressWarnings("unchecked")
    public <T extends ShopDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
