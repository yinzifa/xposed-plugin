package com.example.xposeddemo;

import android.util.Log;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkHttp {

    private static OkHttpClient okHttpClient;

    static {
//        okhttp自带的拦截器
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
//                .addInterceptor(new MyInterceptor())
//                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static OkHttpClient createOkHttpClient() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
//                .addInterceptor(new MyInterceptor())
                .build();
        return okHttpClient;
    }

    /*//同步get方法
    public static void get(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //构造请求体
                Request request = new Request.Builder().url(url).build();
                //构造请求对象
                Call call = okHttpClient.newCall(request);
                //execute()发起同步请求
                Response response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(response != null) {
                    try {
                        Log.e("OKhttp","get response:" + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.e("OKhttp","get response:" + "什么也没有");
                }

            }
        }).start();

//        return request;
    }
*/

   /* //同步post方法
    public static void post(String url, String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject json = new JSONObject();
                try {
                    json.put("content","123456");
                    json.put("postId","22");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType mediaType = MediaType.parse("text/plain;charset=utf-8");
                RequestBody requestBody = RequestBody.Companion.create(String.valueOf(json),mediaType);
                //构造请求体
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                //构造请求对象
                Call call = okHttpClient.newCall(request);
                //execute()发起同步请求
                Response response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(response != null) {
                    try {
                        Log.e("OKhttp","get response:" + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.e("OKhttp","get response:" + "什么也没有");
                }

            }
        }).start();
    }*/

    //异步post请求-提交字符串
    public static void postAsync(String url, String data) {
        MediaType mediaType = MediaType.parse("text/plain;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, data);
        //构造请求体
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        //构造请求对象
        Call call = okHttpClient.newCall(request);
        //enqueue()发起异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Okhttp","get response onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("Okhttp","get response onResponse");
                Log.i("Okhttp",response.body().string());
            }
        });
    }
    //异步post请求-表单提交
    public static void postFormAsync(String url,String data) {
        FormBody formBody = new FormBody.Builder()
                .add("msg",data)
                .add("userId","yinzifa")
                .build();
        //构造请求体
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //构造请求对象
        Call call = okHttpClient.newCall(request);
        //enqueue()发起异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Okhttp","get response onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("Okhttp","get response onResponse");
                Log.i("Okhttp",response.body().string());
            }
        });
    }

   /* //异步post请求-上传文件[多表单上传]
    public static void postFormUploadFileAsync(Context context, String url) {
        //读取存储卡文件，在android6.0及以后，读取外部存储卡的文件需要动态申请权限
        File file = new File(Environment.getExternalStorageDirectory(),"1.png");
        if(!file.exists()) {
            Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show();
            return;
        }
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody requestBody = RequestBody.Companion.create(file,mediaType);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("userId","12345678")
                .addFormDataPart("tagId","22")
                .addFormDataPart(
                        "file",
                        "file.png",
                        requestBody)
                .build();
        //构造请求体
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        //构造请求对象
        Call call = okHttpClient.newCall(request);
        //enqueue()发起异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Okhttp","get response onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("Okhttp","get response onResponse");
                Log.i("Okhttp",response.body().string());
            }
        });
    }*/
}
