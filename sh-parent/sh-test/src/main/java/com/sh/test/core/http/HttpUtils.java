package com.sh.test.core.http;

import com.alibaba.fastjson.JSONObject;
import com.sh.test.core.config.Constants;
import com.sh.test.core.util.LogHelper;
import com.sh.utils.common.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HttpUtils {

    // 加了这个前缀是为了避免定义的XXXReqBean/XXXXResBean中出现重复的字段（原因是文档定义中有一个参数名，JSON部分数据解析出来也有个参数名，这两部分可能是一样的）
    private final static String CHARSET = "UTF-8";
    private final static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(
                    Integer.parseInt(Constants.HTTPPOST_CONNECTTIMEOUT == null ? "10000" : Constants.HTTPPOST_CONNECTTIMEOUT)) // 连接超时
            .setSocketTimeout(
                    Integer.parseInt(Constants.HTTPPOST_SOCKETTIMEOUT == null ? "30000" : Constants.HTTPPOST_SOCKETTIMEOUT)) // 请求获取数据的超时时间，单位毫秒。
            // 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
            .setConnectionRequestTimeout(1000) // 设置从connect Manager获取Connection
            // 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
            .build();

    /**
     * 发送HTTP请求
     * <p>
     * 响应内容的Map对象
     *
     * @return 返回对方接收到内容后给的反馈
     * @throws Exception
     * @author 范玖祎
     * @date 16-03-28
     */
    public static String sendMsg(JSONObject jsonObject, String url) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String responseBody = "";
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            Set<String> keySet = jsonObject.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, jsonObject.getString(key)));
            }
            LogHelper.info("[sh-test] reqBody:[" + nvps.toString() + "]");
            httpClient = CustomHttpClient.GetHttpClient();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            HttpResponse hr = null;
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
            hr = httpClient.execute(httpPost);
            responseBody = EntityUtils.toString(hr.getEntity());
            LogHelper.info("[sh-test] resStatus:[" + hr.getStatusLine() + "] resBody:[" + responseBody + "]");

            if(StringUtils.isEmpty(responseBody))
                throw new RuntimeException("url:["+url+"],响应数据为空.");

        } catch (Exception e) {
            LogHelper.error("HTTP通信异常 e:"+e);
        } finally {
            if (httpPost != null) {
                try {
                    httpPost.getEntity().getContent().close();
                } catch (Exception e) {
                    LogHelper.error("关闭通信通道异常，请联系清结算运维，核对订单情况。");
                }
            }
        }
        return responseBody;
    }


}
