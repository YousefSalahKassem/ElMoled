package com.elmoledmol.www;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class processsuccess extends AppCompatActivity {
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processsuccess);
        getSupportActionBar().hide();
//        progressBar=findViewById(R.id.progress);
//        new Task().execute();

    }

    /*class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.GONE);
            progressBar.setIndeterminate(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressBar.setVisibility(View.VISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            return null;
        }
    }*/
}