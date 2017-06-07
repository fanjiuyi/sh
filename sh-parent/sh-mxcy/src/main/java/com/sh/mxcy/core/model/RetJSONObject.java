package com.sh.mxcy.core.model;

import com.alibaba.fastjson.JSONObject;
import com.sh.utils.common.StringUtils;
import com.sh.utils.config.Constants;


/**
 * Service 返回参数封装
 *
 * @author Administrator
 */
public class RetJSONObject extends JSONObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 判断是否成功
     *
     * @return
     */
    public Boolean isSuccess() {
        return Constants.RES_SUCCESS.equals(this.get("rtncode"));
    }

    public RetJSONObject setSuccess() {
        this.setCode(Constants.RES_SUCCESS);
        this.setMsg("操作成功");
        return this;
    }


    /**
     * 设置错误消息返回
     *
     * @param code
     */
    public RetJSONObject setErrorRet(String code, String extMsg) {
        if (StringUtils.isNotEmpty(extMsg) && extMsg.length() > 256) {
            extMsg = extMsg.substring(0, 255) + " ... ";
        }
        this.setCode(code);
        this.setMsg(extMsg);
        return this;
    }


    /**
     * 设置返回吗
     *
     * @param v
     */
    public RetJSONObject setCode(String v) {
        this.put("rtncode", v);
        return this;
    }

    /**
     * 设置返回消息
     *
     * @param v
     */
    public RetJSONObject setMsg(String v) {
        if (StringUtils.isEmpty(v)) {
            v = "消息为空";
        }
        this.put("rtnmsg", v);
        return this;
    }

    /**
     * @return
     */
    public String getCode() {
        return this.getString("rtncode");
    }

    /**
     * @return
     */
    public String getMsg() {
        return this.getString("rtnmsg");
    }

    /**
     * 设置数据
     *
     * @param jObject
     */
    public RetJSONObject setBusiParam(JSONObject jObject) {
        this.put("busiparam", jObject);
        return this;
    }

    public JSONObject getBusiParamJson() {
        JSONObject bus = this.getJSONObject("busiparam");
        return (bus == null ? (new JSONObject()) : bus);
    }

    public RetJSONObject removeBusiParam() {
        this.remove("busiparam");
        return this;
    }

    public JSONObject getBusiParamAndRemoveJson() {
        JSONObject bus = this.getBusiParamJson();
        this.removeBusiParam();
        return bus;
    }


}
