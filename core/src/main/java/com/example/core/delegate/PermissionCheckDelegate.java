package com.example.core.delegate;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.core.camera.CameraImageEntity;
import com.example.core.camera.RequestCode;
import com.example.core.camera.ShopCamera;
import com.example.core.util.callback.CallbackManager;
import com.example.core.util.callback.CallbackType;
import com.example.core.util.callback.IGlobalCallback;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

//权限检查
@RuntimePermissions
public abstract class PermissionCheckDelegate extends BaseDelegate {

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        ShopCamera.start(this);
    }

    public void startCameraWithCheck() {
        PermissionCheckDelegatePermissionsDispatcher.showCameraWithPermissionCheck(this);
    }

    //用户拒绝相机请求
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {

    }

    //拒绝后不再询问
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {

    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckDelegatePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.TAKE_PHOTO:
                    final Uri resultUri = CameraImageEntity.getInstance().getPath();
                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(),this);
                    break;
                case RequestCode.PICK_PHOTO:
                    if(data != null){
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = ShopCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCode.SCAN:
                    break;
                case RequestCode.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    @SuppressWarnings("unchecked")
                    IGlobalCallback<Uri> callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback != null){
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCode.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
