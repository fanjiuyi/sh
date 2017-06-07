package com.sh.mxcy.controller;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.controller.base.BaseController;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.core.utils.web.ReturnDataUtil;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.core.SpringContextHolder;
import com.sh.utils.common.StringUtils;
import com.sh.utils.logger.LogHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Fanjiuyi on 2017/4/11.
 */
@Controller
@RequestMapping("/api")
public class ApiGatewayController extends BaseController {


    @RequestMapping("/gateway.do")
    public ModelAndView gateway(HttpServletRequest req, HttpServletResponse res) {
        RetJSONObject ret = new RetJSONObject();
        JSONObject paramJson = new JSONObject();
        try {
            //获取请求数据
            paramJson = HandlePubDataUtil.resolveReqData(req);
            LogHelper.info("in-paramJson:" + paramJson);

            //必要数据校验
            ret = HandlePubDataUtil.checkData(paramJson);

            if (ret.isSuccess()) {

                //将busi_param里面的数据与公共数据合并到一块
                HandlePubDataUtil.paramSum(paramJson);

                //默认
                String beanName = this.getServiceName(paramJson.getString("action_name"));

                ServiceHandlerInvoke exeService = SpringContextHolder.getBean2(beanName);
                //
                if (StringUtils.isEmpty(exeService)) {
                    ret.setErrorRet("011011", "service_is_not_exist");
                } else {
                    //--- exec service
                    LogHelper.info("paramJson:" + paramJson);

                    ret = exeService.handler(paramJson);

                }
            }
        } catch (Exception e) {
            LogHelper.error("execCenter fail:", e);
            ret.setErrorRet("000001","服务异常,请稍后再试.");
        }

        return ReturnDataUtil.writeReturn(paramJson, ret, req, res);
    }
}
