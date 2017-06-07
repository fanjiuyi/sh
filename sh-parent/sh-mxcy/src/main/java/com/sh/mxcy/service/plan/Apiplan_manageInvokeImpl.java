package com.sh.mxcy.service.plan;

import com.alibaba.fastjson.JSONObject;
import com.sh.mxcy.core.model.InDBJSONObject;
import com.sh.mxcy.core.model.OutDBJSONObject;
import com.sh.mxcy.core.model.RetJSONObject;
import com.sh.mxcy.core.utils.web.HandlePubDataUtil;
import com.sh.mxcy.dao.inter.EntrDao;
import com.sh.mxcy.dao.inter.FinDao;
import com.sh.mxcy.dao.inter.PlanDao;
import com.sh.mxcy.service.base.ServiceHandlerInvoke;
import com.sh.utils.logger.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 范玖祎
 * @date 2017/5/18 15:12
 * @description 生产计划--进度管理
 */
@Service("Apiplan_manageInvokeImpl")
public class Apiplan_manageInvokeImpl implements ServiceHandlerInvoke {

    @Autowired
    @Qualifier("PlanDaoImpl")
    PlanDao PlanDao;

    @Autowired
    @Qualifier("FinDaoImpl")
    FinDao FinDao;

    @Autowired
    @Qualifier("EntrDaoImpl")
    EntrDao EntrDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RetJSONObject handler(JSONObject inParamJO) throws Exception {
        RetJSONObject ret = new RetJSONObject();

        InDBJSONObject inDBJSONObject = new InDBJSONObject();

        OutDBJSONObject outDBJSONObject = new OutDBJSONObject();
        //业务数据校验
        ret = HandlePubDataUtil.checkInData(inParamJO, "id", "next_workshop", "type_code");
        if (ret.isSuccess()) {
            //参数复制
            HandlePubDataUtil.paramCopy(inParamJO, inDBJSONObject, "id", "workshop","workshop_code", "next_workshop", "type_code", "remarks","start_time","end_time","entr_unit");

            if(inDBJSONObject.getString("next_workshop").equals(inDBJSONObject.getString("workshop_code"))){
                ret.setErrorRet("011010","当前车间与下一个车间不能相同!");
                return ret;
            }

            //当前车间是外加工车间  查询该生产计划关联的外加工是否完成
            if("1".equals(inDBJSONObject.getString("workshop_code"))){
                int total = EntrDao.query_wc_total(inDBJSONObject);
                if(0<total){
                    ret.setErrorRet("011009","该计划有未完成的委外订单,请先完成对应委外订单");
                    return ret;
                }
            }

            //下一个车间为 外加工车间时生成委外记录数据
            if("1".equals(inDBJSONObject.getString("next_workshop"))){
                //判断生成委外数据 所需的信息是否足够
                ret = HandlePubDataUtil.checkInData(inParamJO, "start_time", "end_time", "entr_unit");
                if (!ret.isSuccess()) {
                    return ret;
                }
                EntrDao.plan_insert(inDBJSONObject);
            }

            //下一个车间为 成品车间时生成 成品仓库的记录数据
            if("2".equals(inDBJSONObject.getString("next_workshop"))){
                FinDao.insert(inDBJSONObject);

                inDBJSONObject.put("oper_type","1");

                FinDao.insert_oper(inDBJSONObject);

                inDBJSONObject.put("status","2");
            }
            PlanDao.update(inDBJSONObject);

            PlanDao.oper_insert(inDBJSONObject);

            ret.setBusiParam(outDBJSONObject);

            ret.setSuccess();
        }
        return ret;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("t","1");
        System.out.println("1".equals(jsonObject.getString("workshop")));
    }
}
