package me.jmix.brothertakeaway.handler;

import me.jmix.brothertakeaway.config.ProjectUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // 拦截登录异常
    public ModelAndView handlerAuthorException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize()
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getProject()
                .concat("/sell/seller/login"))));
    }
}
