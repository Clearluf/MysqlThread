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
	 * @param serverip 服务器ip
	 * @param serverport 服务器端口port
	 * @param type 操作类型
	 * @param hostcode 客户端的hostcode
	 * @return
	 */
    public String doGet(String weburl) {
    	String httpurl=weburl;
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
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
            // 关闭资源
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

            connection.disconnect();// 关闭远程连接
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