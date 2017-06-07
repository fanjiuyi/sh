package com.sh.gateway.core.utils.web;

import com.alibaba.fastjson.JSONObject;
import com.sh.utils.common.BusiDataUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.stereotype.Component;

/**
 * @author 范玖祎
 * @date 2017/4/18 15:21
 * @description 短信发送工具类
 */
@Component("SendSmsUtils")
public class SendSmsUtils {

    public String send(String recNum,String smsCode) throws ApiException {

        String url = BusiDataUtils.getInitPro("sendSms_url");

        String appkey = BusiDataUtils.getInitPro("sendSms_appkey");

        String secret = BusiDataUtils.getInitPro("sendSms_secret");

        String smsFreeSignName = BusiDataUtils.getInitPro("sendSms_smsFreeSignName");

        String smsTemplateCode = BusiDataUtils.getInitPro("sendSms_smsTemplateCode");

        JSONObject SmsParam = new JSONObject();

        SmsParam.put("smsCode", smsCode);

        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");
        req.setSmsFreeSignName(smsFreeSignName);
        req.setSmsParamString(SmsParam.toJSONString());
        req.setRecNum(recNum);
        req.setSmsTemplateCode(smsTemplateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);

        return rsp.getBody();
    }
}
