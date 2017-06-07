package com.sh.mxcy.service.plan;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.CommDao;
import com.sh.mxcy.dao.inter.MaterialDao;
import com.sh.mxcy.dao.inter.PlanDao;
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
 * @description 生产计划--审核
 */
@Service("Apiplan_auditInvokeImpl")
public class Apiplan_auditInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PlanDaoImpl")
    PlanDao PlanDao;

    @Autowired
    @Qualifier("CommDaoImpl")
    CommDao CommDaoImpl;

    @Autowired
    @Qualifier("MaterialDaoImpl")
    MaterialDao MaterialDao;

    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        OutDBJSONObject outDBJSONObject = new OutDBJSONObject();

        List<OutDBJSONObject> outDBJSONObjectList;
        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "id", "status_code");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "id", "status_code");
            //参数设置
            inDBJSONObject.put("reviewer", "BOSS");
            inDBJSONObject.put("page_index","1");
            inDBJSONObject.put("page_size","10");

            //处理分页参数
            HandlePubDataUtil.limitParmHandle(inDBJSONObject);

            if ("1".equals(inDBJSONObject.getString("status_code"))) {
                inDBJSONObject.remove("status_code");
                //查询数据
                outDBJSONObjectList = PlanDao.query(inDBJSONObject);

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
                if(!"0".equals(outDBJSONObject.getString("status_code"))){
                    ret.setErrorRet("011007","数据不允许审核状态");
                    return ret;
                }
                String me_type_code = outDBJSONObject.getString("me_type_code");
                inDBJSONObject.put("type_code",me_type_code);

                OutDBJSONObject comm_OutDBJSONObject = CommDaoImpl.getMaterialNum(inDBJSONObject);

                if(comm_OutDBJSONObject.getInteger("total")<outDBJSONObject.getInteger("order_num")) {
                    ret.setErrorRet("011008", "原材料库存不足");
                    return ret;
                }
                InDBJSONObject materialInDBJSONObject = new InDBJSONObject();
                materialInDBJSONObject.put("page_index","1");
                materialInDBJSONObject.put("page_size","10");
                materialInDBJSONObject.put("type_code",me_type_code);
                materialInDBJSONObject.put("status_code","1");
                materialInDBJSONObject.put("sur_not_zero","1");
                //处理分页参数
                HandlePubDataUtil.limitParmHandle(materialInDBJSONObject);

                List<OutDBJSONObject>  materialOutDBJSONObjectList = MaterialDao.query(materialInDBJSONObject);

                Integer num = outDBJSONObject.getInteger("order_num");
                for(int i=0;i<materialOutDBJSONObjectList.size();i++){
                    OutDBJSONObject  materialOutDBJSONObject = materialOutDBJSONObjectList.get(i);

                    if(num<=materialOutDBJSONObject.getInteger("sur_num")){
                        materialInDBJSONObject.clear();
                        materialInDBJSONObject.put("id",materialOutDBJSONObject.getString("id"));
                        materialInDBJSONObject.put("sur_num",materialOutDBJSONObject.getInteger("sur_num")-num);
                        materialInDBJSONObject.put("insert_oper_num",num);
                        materialInDBJSONObject.put("oper_type","2");
                        MaterialDao.update(materialInDBJSONObject);
                        MaterialDao.insert_oper(materialInDBJSONObject);
                        break;
                    }else{
                        num-=materialOutDBJSONObject.getInteger("sur_num");
                        materialInDBJSONObject.clear();
                        materialInDBJSONObject.put("id",materialOutDBJSONObject.getString("id"));
                        materialInDBJSONObject.put("pro_plan_id",materialOutDBJSONObject.getString("id"));
                        materialInDBJSONObject.put("insert_oper_num",materialOutDBJSONObject.getString("sur_num"));
                        materialInDBJSONObject.put("sur_num","0");
                        materialInDBJSONObject.put("oper_type","2");

                        MaterialDao.update(materialInDBJSONObject);
                        MaterialDao.insert_oper(materialInDBJSONObject);
                    }

                }
                inDBJSONObject.put("status_code","1");
                PlanDao.oper_insert(inDBJSONObject);
            }

            PlanDao.audit(inDBJSONObject);
            outDBJSONObject.clear();
            ret.setBusiParam(outDBJSONObject);
            ret.setSuccess();

        }
        return ret;
    }

}
