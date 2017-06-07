package com.sh.mxcy.service.parts;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.MaterialDao;
import com.sh.mxcy.dao.inter.PartsDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 配件-出库
 */
@Service("Apiparts_outInvokeImpl")
public class Apiparts_outInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PartsDaoImpl")
    PartsDao PartsDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        OutDBJSONObject outDBJSONObject;

        List<OutDBJSONObject> outDBJSONObjectList;

        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "id", "num", "user", "use_work");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "id", "num", "user", "use_work");

            //设置分页参数
            inDBJSONObject.put("page_index","1");
            inDBJSONObject.put("page_size","10");

            //处理分页参数
            HandlePubDataUtil.limitParmHandle(inDBJSONObject);

            //查询数据
            outDBJSONObjectList = PartsDao.in_query(inDBJSONObject);
            //判断数据 是否合法
            if(outDBJSONObjectList.isEmpty()||outDBJSONObjectList.size()<=0){
                ret.setErrorRet("011004","数据库数据状态非法");
                return ret;
            }

            outDBJSONObject = outDBJSONObjectList.get(0);
            if(outDBJSONObject.isEmpty()){
                    ret.setErrorRet("011004","数据库数据状态非法");
                    return ret;
            }
            if("0".equals(outDBJSONObject.getString("status_code"))){
                ret.setErrorRet("011006","未审核,不允许操作");
                return ret;
            }

            if(inDBJSONObject.getInteger("num")>outDBJSONObject.getInteger("sur_num")) {
                ret.setErrorRet("011005", "出库数量大于库存量");
                return ret;
            }

            PartsDao.out(inDBJSONObject);

            PartsDao.out_oper(inDBJSONObject);

            ret.setBusiParam(outDBJSONObject);

            ret.setSuccess();
        }
        return ret;
    }

}
