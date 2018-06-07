package com.example.core.camera;

import android.net.Uri;

import com.example.core.delegate.PermissionCheckDelegate;
import com.example.core.util.file.FileUtil;

public class ShopCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
