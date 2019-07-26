package com.hb56.client.edo.collect.listener;

import com.hb56.client.edo.collect.model.Article;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


@Component
@Slf4j
public class EdoListener {
    private String serviceName;
    private String edoServiceName;

    @RabbitHandler
    @RabbitListener(queues = "esb.blockchain.queue")
    public void onMessage(Article article) throws UnsupportedEncodingException {
        serviceName = article.getServiceName();
        String requestBody ="";
        String responseBody = new String(article.getMsg_response_blob(), "utf-8");

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");


        if(serviceName.equals("S0290003A")){
            /**
             * Edo同步创建接口
             */
            edoServiceName = "S1150002A";
        }else if (serviceName.equals("S0290009A")){
            //异步创建edo
            edoServiceName = "S1150002A";
        }else if (serviceName.equals("S0290005A")){
            //授权edo
            edoServiceName = "S1150004A";
        }else if(serviceName.equals("S0290004A")){
            //取消报文
            edoServiceName = "S1150003A";
        }else if(serviceName.equals("S0290010A")){
            //查询edo报文
            edoServiceName = "S1150005A";
        }
        if (serviceName.equals("S0290003A")||serviceName.equals("S0290005A")||serviceName.equals("S0290009A")){
            requestBody = new String(Base64.getDecoder().decode(article.getMsg_request_blob()), "utf-8");
            log.info("1: "+requestBody);
            log.info("1: "+responseBody);
            sendEdo2BC(mediaType,requestBody,responseBody);
        }else if(serviceName.equals("S0290004A")){
            requestBody = new String(article.getMsg_request_blob(), "utf-8");
            log.info("2: "+requestBody);
            log.info("2: "+responseBody);
            sendEdo2BC(mediaType,requestBody,responseBody);
        }else if(serviceName.equals("S0290010A")){
            handleReceipt(mediaType,responseBody);
        }


        log.info(requestBody);
    }

    private void handleReceipt(MediaType mediaType, String responseBody){
        //提交回执
        Request request2 = new Request.Builder()
                .url("http://172.16.39.224:7080/ParaEsb/Json/Http")
                .addHeader("serviceName","S1150005A")
                .addHeader("targetSystem","BLOCK_CHAIN")
                .addHeader("sourceSystem","UPS")
                .post(RequestBody.create(mediaType, responseBody))
                .build();

        OkHttpClient okHttpClient2 = new OkHttpClient();

        okHttpClient2.newCall(request2).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                log.error( "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info( "onResponse receipt: " + response.body().string());
            }
        });
    }

    private void sendEdo2BC(MediaType mediaType, String message,String responseBody){
        Request request = new Request.Builder()
                .url("http://172.16.39.224:7080/ParaEsb/Json/Http")
                .addHeader("serviceName",edoServiceName)
                .addHeader("targetSystem","BLOCK_CHAIN")
                .addHeader("sourceSystem","UPS")
                .post(RequestBody.create(mediaType, message))
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error( "onFailure: " + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info(response.protocol() + " " +response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    log.info(headers.name(i) + ":" + headers.value(i));
                }
                log.info( "onResponse: " + response.body().string());

                if (serviceName.equals("S0290003A")||serviceName.equals("S0290005A")||serviceName.equals("S0290004A")){
                    log.info("receipt: "+ responseBody);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handleReceipt(mediaType,responseBody);
                }
            }
        });
    }
}
