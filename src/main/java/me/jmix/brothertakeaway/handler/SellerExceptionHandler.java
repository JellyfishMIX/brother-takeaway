package me.jmix.brothertakeaway.handler;

import me.jmix.brothertakeaway.config.ProjectUrlConfig;
import me.jmix.brothertakeaway.exception.SellException;
import me.jmix.brothertakeaway.exception.SellerAuthorizeException;
import me.jmix.brothertakeaway.utils.ResultVOUtil;
import me.jmix.brothertakeaway.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // 拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize()
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getProject()
                .concat("/sell/seller/login"))));
    }

    // 拦截卖家异常
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getExceptionClassName(), e.getStateCode(), e.getStateInfo());
    }
}
