package com.example.core.camera;

import com.yalantis.ucrop.UCrop;

public class RequestCode {

    //拍照
    public static final int TAKE_PHOTO = 4;
    //从图库选择照片
    public static final int PICK_PHOTO = 5;
    //使用相机扫描
    public static final int SCAN = 6;

    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
}
