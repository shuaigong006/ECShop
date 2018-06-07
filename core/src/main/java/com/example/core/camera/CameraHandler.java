package com.example.core.camera;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.example.core.R;
import com.example.core.delegate.PermissionCheckDelegate;
import com.example.core.util.file.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraHandler implements View.OnClickListener{

    private Uri imageUri = null;
    private final AlertDialog DIALOG;
    private final PermissionCheckDelegate DELEGATE;

    public CameraHandler(PermissionCheckDelegate delegate) {
        this.DELEGATE = delegate;
        this.DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    //开启相机选择框(拍照或打开图库)
    public final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null){
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
        }
    }

    //拍照
    private void takePhoto() {
        //获取系统版本
        final int currentVersion = android.os.Build.VERSION.SDK_INT;
        //激活相机
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断储存卡是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
            final String filename = format.format(new Date());
            final File tempFile = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
            if (currentVersion < Build.VERSION_CODES.N) {
                //从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                CameraImageEntity.getInstance().setPath(imageUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                final ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(DELEGATE.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
//                    DELEGATE.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    Toast.makeText(DELEGATE.getContext(),"请开启存储权限",Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = DELEGATE.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                final File realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(DELEGATE.getContext(),imageUri));
                final Uri realUri = Uri.fromFile(realFile);
                CameraImageEntity.getInstance().setPath(realUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        DELEGATE.startActivityForResult(intent,RequestCode.TAKE_PHOTO);
    }

    //选取图片
    private void pickPhoto() {
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult
                (Intent.createChooser(intent, "选择获取图片的方式"), RequestCode.PICK_PHOTO);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            takePhoto();
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
            DIALOG.cancel();
        }
    }
}
