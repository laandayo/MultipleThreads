package com.lan.multiplethreads;

import static android.content.ContentValues.TAG;
import static android.net.wifi.WifiConfiguration.Status.strings;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pbCounter;
    private TextView tvMessage;
    private Button btnCounter;
    private static final int MAX = 100000;

    private ImageView imvAvatar; // 1. Declare the field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbCounter = (ProgressBar)findViewById(R.id.pb_counter);
        pbCounter.setMax(MAX);
        tvMessage = (TextView)findViewById(R.id.tv_message);
        btnCounter = (Button)findViewById(R.id.btn_counter);
        imvAvatar = (ImageView)findViewById(R.id.imv_avatar); // 2. Initialize the field

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateProgresBarTask().execute(MAX);
                btnCounter.setEnabled(false);
            }
        });
    }

    class UpdateProgresBarTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvMessage.setText("Update");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int n = params[0];
            for(int i = 0; i < n; i++)
                publishProgress(i);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbCounter.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvMessage.setText("End");
            btnCounter.setEnabled(true);
        }
    }





    class DownImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute:");
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.e(TAG, "doInBackground:");
            Bitmap bm = null;
            try {
                URL aURL = new URL(strings[0]);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("Hub", "Error getting the image from server : " + e.getMessage());
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.e(TAG, "onPostExecute:");
            super.onPostExecute(bitmap);
            if (bitmap != null && imvAvatar != null) {
                imvAvatar.setImageBitmap(bitmap);
            }
        }
    }
}
