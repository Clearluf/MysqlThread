package JavaHttp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpDemo02 {

    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // ͨ��ַĬ�����ô���һ��httpClientʵ��
            httpClient = HttpClients.createDefault();
            // ����httpGetԶ������ʵ��
            HttpGet httpGet = new HttpGet(url);
            // ��������ͷ��Ϣ����Ȩ
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // ���������������
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// ������������ʱʱ��
                    .setConnectionRequestTimeout(35000)// ����ʱʱ��
                    .setSocketTimeout(60000)// ���ݶ�ȡ��ʱʱ��
                    .build();
            // ΪhttpGetʵ����������
            httpGet.setConfig(requestConfig);
            // ִ��get����õ����ض���
            response = httpClient.execute(httpGet);
            // ͨ�����ض����ȡ��������
            HttpEntity entity = response.getEntity();
            // ͨ��EntityUtils�е�toString���������ת��Ϊ�ַ���
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // ����httpClientʵ��
        httpClient = HttpClients.createDefault();
        // ����httpPostԶ������ʵ��
        HttpPost httpPost = new HttpPost(url);
        // �����������ʵ��
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// ����������������ʱʱ��
                .setConnectionRequestTimeout(35000)// ������������ʱʱ��
                .setSocketTimeout(60000)// ���ö�ȡ�������ӳ�ʱʱ��
                .build();
        // ΪhttpPostʵ����������
        httpPost.setConfig(requestConfig);
        // ��������ͷ
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // ��װpost�������
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // ͨ��map����entrySet������ȡentity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // ѭ����������ȡ������
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // ΪhttpPost���÷�װ�õ��������
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient����ִ��post����,��������Ӧ��������
            httpResponse = httpClient.execute(httpPost);
            // ����Ӧ�����л�ȡ��Ӧ����
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}