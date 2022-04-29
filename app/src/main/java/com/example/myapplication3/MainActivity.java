package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    protected List<Map<String, Object>> dataSet;
    public static final String EXTRA_MESSAGE = "com.example.myapplication3.MESSAGE";

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
                map.put("title", "我说你别狗叫了可以吗");
            } else {
                Log.d("i%2", String.valueOf(i % 2 == 0));
                map.put("title", "说不上爱别纠缠");
            }
            dataSet.add(map);
            map = new HashMap<>();
        }
    }
}