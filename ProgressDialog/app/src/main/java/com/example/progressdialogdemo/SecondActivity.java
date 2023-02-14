package com.example.progressdialogdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    Button btnDownload;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] fileUrls = {
                        "file1",
                        "file2",
                        "file3",
                        "file4"
                };
                new Downloader(SecondActivity.this,new MyHandler()).execute(fileUrls);
            }
        });

    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.obj == null || msg == null){
                return;
            }

            if(msg.what == 1){

                Float progress = (Float) msg.obj;
                btnDownload.setText("Progress" + progress +"%");
            }
            if(msg.what == 2){
                Integer [] result = (Integer[]) msg.obj;
                btnDownload.setText("result" + result);
            }
        }
    }
}
