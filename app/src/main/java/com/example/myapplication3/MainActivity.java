package com.example.myapplication3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    protected List<Map<String, Object>> dataSet;
    public static final String EXTRA_MESSAGE = "com.example.myapplication3.MESSAGE";
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private final VideoView mVideoView = findViewById(R.id.video);

    @Override
    protected void onResume() {
        super.onResume();
        requestPermissions();
    }

    private void startVideo() {
        // don't forget read permission in menifest
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File f = new File(path, "/Camera/VID_20220428_092919.mp4");
        if (f.exists())
            mVideoView.setVideoPath(f.getAbsolutePath());

        mVideoView.start();
    }

    private void requestForReadExternalStorage() {
        //Android6.0以上需要动态申请权限
        //检查是否已有权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            startVideo();
        } else if (shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ;
        } else {
            requestPermissionLauncher =
                    registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                        if (isGranted) {
                            // Permission is granted. Continue the action or workflow in your
                            // app.
                            startVideo();
                        } else {
                            // Explain to the user that the feature is unavailable because the
                            // features requires a permission that the user has denied. At the
                            // same time, respect the user's decision. Don't link to system
                            // settings in an effort to convince the user to change their
                            // decision.
                        }
                    });
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListData();
        RecyclerView recList = findViewById(R.id.recView);
        recList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recList.setAdapter(new SdAdapter(dataSet));
    }

    private void initListData() {
        dataSet = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put("thumb", R.drawable.bark);
            Log.d("i%2", String.valueOf(i % 2 == 0));
            if (i % 2 == 0) {
                map.put("title", "天青色等烟雨");
            } else {
                Log.d("i%2", String.valueOf(i % 2 == 0));
                map.put("title", "说不上爱别纠缠");
            }
            dataSet.add(map);
            map = new HashMap<>();
        }
    }
}