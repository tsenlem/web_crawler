package com.web.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpPostTest {
    public static void main(String[] args) {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //声明访问地址
        HttpPost httpPost = new HttpPost("https://www.autohome.com.cn/bestauto/");

        CloseableHttpResponse response = null;
        //发起请求
        try {
            response = httpClient.execute(httpPost);

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
