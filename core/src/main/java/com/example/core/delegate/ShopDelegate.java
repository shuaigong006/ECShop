package com.example.core.delegate;

public abstract class ShopDelegate extends PermissionCheckDelegate{

    @SuppressWarnings("unchecked")
    public <T extends ShopDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
