package com.example.progressdialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new BtnDownloadClickListener());
    }

    class BtnDownloadClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String [] fileUrls = {
                    "file1",
                    "file2",
                    "file3",
                    "file4"
            };
            new DownloadThread().execute(fileUrls);
        }
    }

    class DownloadThread extends AsyncTask<String,Integer,Float>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Progress Dialog");
            progressDialog.setMessage("Downloading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected Float doInBackground(String... fileUrls) {
            for(String fileUrl : fileUrls){
                for(int i = 0; i<100;i++){
                    Log.e("tag","downloading"+fileUrl +"--"+ i + "%");
                    try{
                        Thread.sleep(50);
                    } catch (InterruptedException e){
                        Log.e("exception Occurred","$e"+e.getMessage());
                    }
                    Integer [] progress = new Integer[1];
                    progress[0] = i;
                    publishProgress(i);
                    progressDialog.setProgress(i);
                }
            }
            return 12.20F;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            btnDownload.setText("Progress" + values[0] +"%");
        }

        @Override
        protected void onPostExecute(Float aFloat) {
            super.onPostExecute(aFloat);
            btnDownload.setText("Float " + aFloat);
        }
    }


}