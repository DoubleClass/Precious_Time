package com.example.hello;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2;
    ImageView m1, m2;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "*&*&*&*&*&*&*&*&*&*");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        init();
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                String name = e1.getText().toString();
                String pwd = e2.getText().toString();
                try {
                    jsonObject.put("name", name);
                    jsonObject.put("pw", pwd);
                } catch (Exception e) {
                    System.out.println("error");
                    System.out.println(e);
                }
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
                String jsonStr = "{\"name\":\"xiaoming\",\"pw\":\"123\"}";//json数据.
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                System.out.println(jsonObject.toString());
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8000/user/login/")
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String sss = response.body().string();
                    System.out.println("Sucess");
                    System.out.println(sss);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    private void init() {
        // TODO Auto-generated method stub
        e1 = (EditText) findViewById(R.id.phonenumber);
        e2 = (EditText) findViewById(R.id.password);
        m1 = (ImageView) findViewById(R.id.del_phonenumber);
        m2 = (ImageView) findViewById(R.id.del_password);
        bt = (Button)findViewById(R.id.loginButton);

//�������������
        EditTextClearTools.addclerListener(e1, m1);
        EditTextClearTools.addclerListener(e2, m2);
        System.out.println(e1.getText().toString());
    }
}
