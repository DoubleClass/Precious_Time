package com.example.hello;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    EditText e1, e2;
    ImageView m1, m2;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        Resources res =getResources();
        final String[] ways=res.getStringArray(R.array.signway);//将province中内容添加到数组city中
        spinner = (Spinner) findViewById(R.id.spinner_login);//获取到spacer1
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ways);//创建Arrayadapter适配器
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//通过此方法为下拉列表设置点击事件
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i,long l) {
                String text= spinner.getItemAtPosition(i).toString();
                for(int j=0;j<ways.length;j++){
                    if(text==ways[j]){
                        System.out.println(j);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

//        String[] ctype = new String[]{"手机号", "邮箱", "用户名"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);     //设置下拉列表框的下拉选项样式
//        Spinner spinner = super.findViewById(R.id.spinner_login);
//        spinner.setSelection(1);
//
//        spinner.setAdapter(adapter);
        //System.out.println(spinner.getSelectedItem().toString());
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
