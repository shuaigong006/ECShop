package com.example.core.camera;

import android.net.Uri;

public class CameraImageEntity {

    private Uri mPath = null;

    private static final CameraImageEntity INSTANCE = new CameraImageEntity();

    public static CameraImageEntity getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
