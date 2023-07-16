package com.web.crawler;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpConfigTest {
    public static void main(String[] args) {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //声明访问地址
        HttpGet httpGet = new HttpGet("https://www.autohome.com.cn/bestauto/");

        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //创建连接最长时间，单位毫秒
                .setConnectionRequestTimeout(500)   //设置获取连接的最长时间，单位是毫秒
                .setSocketTimeout(10 * 1000).build();

        //给请求设置请求信息
        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        //发起请求
        try {
            response = httpClient.execute(httpGet);

            //判断状态码是否是200
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
