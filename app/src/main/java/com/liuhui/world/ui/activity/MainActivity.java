package com.liuhui.world.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhui.world.Constant;
import com.liuhui.world.R;
import com.liuhui.world.base.BaseActivity;
import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.ui.fragment.MainFragment;
import com.liuhui.world.utils.FileUtil;
import com.liuhui.world.utils.ImageLoaderUtil;
import com.liuhui.world.utils.LogUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;

import static com.liuhui.world.widget.PhotoClip.ClipViewLayout.getRealFilePathFromUri;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.fl_left)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    private Fragment mMainFragment;
    private PopupWindow mPopupWindow;
    //请求相机
    private static final int REQUEST_CAMERA = 100;
    //请求相册
    private static final int REQUEST_ALBUM = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    private ImageView mPhoto;
    private File tempFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initWindow();
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//设置drawlerlayout不可以滑动打开
       /* ViewGroup.LayoutParams layoutParams = mFragmentLeft.getLayoutParams();
        layoutParams.width = DensityUtil.getDisplayWidh(this) * 4 / 5;*/
        mPhoto = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.photo);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        fm.beginTransaction().add(R.id.fl_body, mMainFragment).commit();
        fm.beginTransaction().hide(mMainFragment).commit();//这里为了实现懒加载所以要先隐藏,再展现
        fm.beginTransaction().show(mMainFragment).commit();

        tempFile = new File(Constant.SAVE_URL + Constant.PHOTO_FILENAME);
        if (tempFile.exists()) {
            ImageLoaderUtil.displayCircle(mPhoto, tempFile.getAbsolutePath());
        }
        initListener();
    }

    MenuItem.OnMenuItemClickListener itemClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.app_bar_search:
                    Toast.makeText(mContext, "查询", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.home:
                    Toast.makeText(mContext, "首页", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.discovery:
                    Toast.makeText(mContext, "发现", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.follow:
                    Toast.makeText(mContext, "关注", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.switch_theme:
                    Toast.makeText(mContext, "切换主题", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.setting:
                    Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    private void initListener() {
        mPhoto.setOnClickListener(this);
        mNavigationView.getMenu().findItem(R.id.home).setOnMenuItemClickListener(itemClickListener);
        mNavigationView.getMenu().findItem(R.id.discovery).setOnMenuItemClickListener(itemClickListener);
        mNavigationView.getMenu().findItem(R.id.follow).setOnMenuItemClickListener(itemClickListener);
        mNavigationView.getMenu().findItem(R.id.switch_theme).setOnMenuItemClickListener(itemClickListener);
        mNavigationView.getMenu().findItem(R.id.setting).setOnMenuItemClickListener(itemClickListener);
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    long firstExitTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - firstExitTime < 2000) {
            System.exit(0);
        } else {
            firstExitTime = currentTime;
            Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo:
                showPopupWindow();
                break;
            case R.id.btn_camera:
                requestPermission(REQUEST_CAMERA, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.btn_photo:
                requestPermission(REQUEST_ALBUM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.btn_cancel:
                mPopupWindow.dismiss();
                break;
        }
    }

    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCamera = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        mPopupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        btnCamera.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            FileUtil.initDirectory(Constant.SAVE_URL);
            FileUtil.initFile(Constant.PHOTO_FILENAME);
            tempFile = new File(Constant.SAVE_URL + Constant.PHOTO_FILENAME);
            if (requestCode == REQUEST_ALBUM) gotoAlbum();
            else if (requestCode == REQUEST_CAMERA) gotoCamera();
            mPopupWindow.dismiss();
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            if (requestCode == REQUEST_ALBUM) {
                // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
                if (AndPermission.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                    // 第一种：用默认的提示语。
                    AndPermission.defaultSettingDialog(mContext, REQUEST_ALBUM).show();
                }
            }else if (requestCode == REQUEST_CAMERA){
                if (AndPermission.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                    // 第一种：用默认的提示语。
                    AndPermission.defaultSettingDialog(mContext, REQUEST_CAMERA).show();
                }
            }
        }
    };

    private void requestPermission(int requestCode, String... permission) {
        AndPermission.with(mContext)
                .permission(permission)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(mContext, rationale).show();
                    }
                })
                .requestCode(requestCode)
                .callback(listener)
                .start();
    }

    /**
     * 跳转到相册
     */
    private void gotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_ALBUM);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e(resultCode + "不成功");
        switch (requestCode) {
            case REQUEST_CAMERA: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_ALBUM:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) return;
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    saveFile(bitMap, Constant.SAVE_URL, Constant.PHOTO_FILENAME);
                    ImageLoaderUtil.displayCircle(mPhoto, cropImagePath);
                }
                break;
        }
    }

    /**
     * 将Bitmap转换成文件保存本地
     */
    public static File saveFile(Bitmap bm, String path, String fileName) {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = null;
        BufferedOutputStream bos = null;
        try {
            myCaptureFile = new File(path, fileName);
            bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
        return myCaptureFile;
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) return;

        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 1);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
}
