package com.example.progressdialogdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class Downloader extends AsyncTask<String,Integer,Float> {
    ProgressDialog progressDialog;
    Context context;
    Handler handler;

    public Downloader(Context context,Handler handler){
        this.context = context;
        this.handler = handler;
    }
    @Override
    protected Float doInBackground(String... strings) {


        return 13.34F;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Float progress) {
        super.onPostExecute(progress);
        Message message = new Message();
        message.obj = progress;
        message.what = 1;
        handler.sendMessage(message);
    }

    @Override
    protected void onProgressUpdate(Integer... result) {
        super.onProgressUpdate(result);
        Message message = new Message();
        message.obj = result;
        message.what = 2;
        handler.sendMessage(message);
    }
}
