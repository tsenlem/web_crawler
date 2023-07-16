package com.web.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AutohomeCrawlerTest {
    public static void main(String[] args) throws Exception{
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //声明访问地址
        HttpGet httpGet = new HttpGet("https://www.autohome.com.cn/bestauto/");

        //发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //判断状态码是否为200
        if(response.getStatusLine().getStatusCode() == 200){
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(content);
        }
    }
}
