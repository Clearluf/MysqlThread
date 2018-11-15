package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpClient {
	/**
	 * 
	 * @param serverip ������ip
	 * @param serverport �������˿�port
	 * @param type ��������
	 * @param hostcode �ͻ��˵�hostcode
	 * @return
	 */
    public String doGet(String weburl) {
    	String httpurl=weburl;
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// ���ؽ���ַ���
        try {
            // ����Զ��url���Ӷ���
            URL url = new URL(httpurl);
            // ͨ��Զ��url���Ӷ����һ�����ӣ�ǿת��httpURLConnection��
            connection = (HttpURLConnection) url.openConnection();
            // �������ӷ�ʽ��get
            connection.setRequestMethod("GET");
            // �������������������ĳ�ʱʱ�䣺15000����
            connection.setConnectTimeout(15000);
            // ���ö�ȡԶ�̷��ص�����ʱ�䣺60000����
            connection.setReadTimeout(60000);
            // ��������
            connection.connect();
            // ͨ��connection���ӣ���ȡ������
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // ��װ������is����ָ���ַ���
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // �������
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر���Դ
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// �ر�Զ������
        }

        return result;
    }
//
//    public void doPost(String serverip,String serverport,String hostcode,int type,String xml) {
//        try {
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//            //System.out.println(xml);
//
//            HttpPost httpPost = new HttpPost("http://"+serverip+":"+serverport+"/api/3in1/upload?type="+type+"&host="+hostcode); 
//            httpPost.addHeader("Content-Type","multipart/form-data; boundary=------------------------badfe43d157e1084");
//            httpPost.addHeader("Content-Disposition","form-data;name=\"content\"");           
//            StringEntity stringEntity = new StringEntity(xml,"UTF-8");
//            stringEntity.setContentEncoding("UTF-8");  
//
//            httpPost.setEntity(stringEntity);
//
//            //CloseableHttpResponse response = httpclient.execute(httpPost);
//
//
////            System.out.println(httpPost.getEntity());
////            Header[] heads = httpPost.getAllHeaders();
////            for(Header head : heads) {
////            	System.out.println(head);
////            }
//
////            System.out.println("Executing request " + httpPost.getRequestLine());
//
//        //   Create a custom response handler
//            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
//                @Override
//                public String handleResponse(final HttpResponse response)
//                        throws ClientProtocolException, IOException {//                 
//                    int status = response.getStatusLine().getStatusCode();
//                    if (status >= 200 && status < 300) {
//
//                        HttpEntity entity = response.getEntity();
//
//
//                        return entity != null ? EntityUtils.toString(entity) : null;
//                    } else {
//                        throw new ClientProtocolException(
//                                "Unexpected response status: " + status);
//                    }
//                }
//            };          
//            String responseBody = httpclient.execute(httpPost, responseHandler);            
//            System.out.println(responseBody);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}