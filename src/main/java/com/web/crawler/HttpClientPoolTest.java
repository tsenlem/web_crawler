package com.web.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientPoolTest {
    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();

        doGet(pool);
        doGet(pool);
    }

    private static void doGet(PoolingHttpClientConnectionManager pool) {
        //从连接池中，获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool).build();

        //声明访问地址
        HttpGet httpGet = new HttpGet("https://www.autohome.com.cn/bestauto/");

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
