package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String message = intent.getStringExtra("hahahaga");
        Log.d("hahahaga", message);
        TextView textView = findViewById(R.id.textView);
        textView.setText("你点击的是：" + message + "(通过intent.putExtra传递的数据)");
    }
}
