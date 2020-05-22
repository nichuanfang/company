package com.jaychouzzz.sms.component;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.jaychouzzz.common.component.AcsClientManager;
import com.jaychouzzz.common.constants.Region;
import com.jaychouzzz.common.reader.AcsProperties;
import com.jaychouzzz.sms.properties.MailProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Classname MailManager
 * @description 邮件发送管理器
 * @Author chuanfang
 * @Date 2020/5/22 16:00
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class MailManager extends AcsClientManager {

    private MailProperties mailProperties;

    private AcsProperties acsProperties;

    /**
     * 发送邮件
     * @param toAddress 收件人
     * @param subject 主题
     * @return 响应
     */
    public AcsResponse sendSingleMail(String toAddress,String subject,String content) {
        //获取阿里云http客户端
        IAcsClient client = getClient(acsProperties);

        //构建邮件请求

        SingleSendMailRequest sendMailRequest = new SingleSendMailRequest();

        try {
            if(!StrUtil.equalsIgnoreCase(acsProperties.getRegion(), Region.CN_HANGZHOU)) {
                sendMailRequest.setVersion("2017-06-22");
            }
            sendMailRequest.setAccountName(mailProperties.getAccountName());
            sendMailRequest.setFromAlias("jaychouzzz");
            sendMailRequest.setAddressType(mailProperties.getAddressType());
            sendMailRequest.setTagName(mailProperties.getTagName());
            sendMailRequest.setReplyToAddress(mailProperties.getReplyToAddress());
            sendMailRequest.setToAddress(toAddress);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress(“邮箱1,邮箱2”);
            sendMailRequest.setSubject(subject);
            //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
            //注意：文本邮件的大小限制为3M，过大的文本会导致连接超时或413错误
//            sendMailRequest.setHtmlBody();
            sendMailRequest.setTextBody(content);
            //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
            //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
            sendMailRequest.setMethod(MethodType.POST);
            //开启需要备案，0关闭，1开启
            //request.setClickTrace(“0”);
            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            return client.getAcsResponse(sendMailRequest);
        } catch (ClientException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
