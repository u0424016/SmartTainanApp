package com.example.edward_liao.smarttainanapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class CheckActivity extends AppCompatActivity {

    private Button button_check, button_back;
    private TextView textView;
    private ImageView success;

    String status;
    String Place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        button_back = (Button) findViewById(R.id.button_back);
        button_check = (Button) findViewById(R.id.button_check);
        textView = (TextView) findViewById(R.id.textview);
        success = (ImageView) findViewById(R.id.imageView2);

        success.setVisibility(View.INVISIBLE);

        if (textView.getText().toString().equals("已抵達學校")) {
            success.setVisibility(View.VISIBLE);

            button_check.setVisibility(View.INVISIBLE);

        }


    }

    public void setButton_check(View view) {

        toServer();


    }

    public void setButton_back(View view) {

        finish();
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }


    public void toServer() {


        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub

                try {


                    //建立POST Request
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet get = new HttpGet("http://163.18.2.157:8777/place/1");


                    //執行Get
                    HttpResponse httpResponse = httpClient.execute(get);
                    //取得回傳的內容
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String responseString = EntityUtils.toString(httpEntity, "UTF-8");
                    //回傳的內容轉存為JSON物件
                    JSONObject responseJSON = new JSONObject(responseString);
                    //取得Balance的屬性

                    String status = responseJSON.getString("Status");
                    String place = responseJSON.getString("place");


                    System.out.println(status);
                    System.out.println(status);
                    System.out.println(status);
                    System.out.println(status);
                    System.out.println(place);
                    System.out.println(place);
                    System.out.println(place);
                    System.out.println(place);


                    if (status.equals("Success") && place.equals("Tainan")) {
                        textView.setText("小孩已抵達學校");


                    } else {
                        textView.setText("小孩尚未抵達學校");
                    }


                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(null, null, null);


    }
}
