package com.sh.gateway.service.base;

import com.alibaba.fastjson.JSONObject;
import com.sh.gateway.core.model.RetJSONObject;

/**
 * ServiceHandlerInvoke 接口
 */
public interface ServiceHandlerInvoke {

    /**
     * 默认调用 handler Json
     * @param inParamJO
     * @return
     */
    RetJSONObject handler(JSONObject inParamJO);
}
