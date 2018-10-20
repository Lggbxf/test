package cn.edu.mystore.mystore;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.mystore.mystore.base.BaseActivity;
import cn.edu.mystore.mystore.mvp.view.activity.MainActivity;

public class IndexActivity extends BaseActivity {

    private Button btn_enter;

    private final static int REQUEST_CODE_STORAGE = 1;
    private SharedPreferences sp;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_enter = findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enter();
            }
        });


    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_index);
    }

    @Override
    protected void initView() {
        sp = getSharedPreferences("myStore", Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst",true);
        if(!isFirst){
            Enter();
        }
        verifyStoragePermission(this);
    }


    public void Enter(){
        Toast.makeText(this,"Enter方法",Toast.LENGTH_LONG).show();
        sp.edit().putBoolean("isFirst",false).commit();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void verifyStoragePermission(Activity activity) {
        //1.检测权限
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PermissionChecker.PERMISSION_GRANTED) {
            //2.没有权限，弹出对话框申请
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_CODE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            //权限申请成功
            Toast.makeText(this, "授权SD卡权限成功", Toast.LENGTH_SHORT).show();
        } else {
            //权限申请失败
            Toast.makeText(this, "授权SD卡权限失败，可能会影响使用", Toast.LENGTH_SHORT).show();
        }
    }
}
