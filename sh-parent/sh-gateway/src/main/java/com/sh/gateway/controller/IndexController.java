package com.sh.gateway.controller;

import com.sh.gateway.controller.base.BaseController;
import com.sh.utils.common.HtmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Fanjiuyi on 2017/4/14.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index.html")
    public void index(HttpServletResponse res){
            HtmlUtil.writer(res,"hello! 9191~~");
    }
}
