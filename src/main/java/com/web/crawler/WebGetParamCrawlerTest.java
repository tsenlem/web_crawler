package com.web.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebGetParamCrawlerTest {
    public static void main(String[] args) {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //声明访问地址
        URI uri = null;
        try {
            URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com/s");

            BasicNameValuePair basicNameValuePair = new BasicNameValuePair("wd","汽车之家");
            uri = uriBuilder.setParameters(basicNameValuePair).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpGet httpGet = new HttpGet(uri);

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
