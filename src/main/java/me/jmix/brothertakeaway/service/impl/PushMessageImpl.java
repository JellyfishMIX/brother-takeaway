package me.jmix.brothertakeaway.service.impl;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.jmix.brothertakeaway.config.WechatAccountConfig;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.service.PushMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PushMessageImpl implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;
    /**
     * 微信模板消息推送
     * @param orderDTO
     */
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oEpDEwHtszhRqhg2S2R4_hVkVVDE")
                .templateId(accountConfig.getTemplateId().get("orderStatus"))
                .build();
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("first", "亲，请记得收货哦。"));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword1", "微信点餐"));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword2", "18868812345"));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword3", orderDTO.getOrderId()));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword4", orderDTO.getOrderMasterStateEnum().getStateInfo()));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("keyword5", "¥" + orderDTO.getOrderAmount()));
        templateMessage.addWxMpTemplateData(new WxMpTemplateData("remark", "欢迎再次光临！"));

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，errMsg = {}", e.getMessage());
        }
    }
}
