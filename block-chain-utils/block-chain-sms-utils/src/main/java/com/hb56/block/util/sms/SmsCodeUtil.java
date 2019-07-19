package com.hb56.block.util.sms;

import okhttp3.*;

import java.io.IOException;

/**
 * @author Been
 * @date 2019/4/28
 */
public class SmsCodeUtil {

    public static OkHttpClient client = new OkHttpClient();

    public static void getCode(String url, String param) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, param);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("host", "api.e-truck.com.cn")
                .addHeader("apiKey", "OBYG65LFMVVXAMDJNBYG24LOOZXXC3TX")
                .build();
        //创建call
        Call call = client.newCall(request);
        //加入队列，异步操作
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
