package com.example.customertextview.apiservice;

import com.example.customertextview.bean.TestBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * description
 *
 * @author created by ABC
 * @date 2019/5/20 19:48
 */
public interface ApiService {

    @GET("users/{user}/repos")
    Call<List<TestBean>>getData(@Path("user")String user);
}
