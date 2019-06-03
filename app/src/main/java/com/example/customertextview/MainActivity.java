package com.example.customertextview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.customertextview.apiservice.ApiService;
import com.example.customertextview.bean.TestBean;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // initData();
    }

    /**
     * 初始化请求网络数据
     */
    private void initData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        ApiService apiservice=retrofit.create(ApiService.class);

        apiservice.getData("user").enqueue(new Callback<List<TestBean>>() {
            @Override
            public void onResponse(Call<List<TestBean>> call, Response<List<TestBean>> response) {

            }

            @Override
            public void onFailure(Call<List<TestBean>> call, Throwable t) {

            }
        });
    }
}
