package com.jaychouzzz.common.vo;

import com.jaychouzzz.common.constants.SignatureSource;
import lombok.Data;

/**
 * @Classname Signature
 * @description 短信签名实体
 * @Author chuanfang
 * @Date 2020/5/22 11:00
 * @Version 1.0
 */
@Data
public class Signature {
    /**
     * 签名 必填
     */
    private String signature;
    /**
     * 签名来源，申请签名时必须指定签名来源。必填
     *      *                  取值范围为： {@link SignatureSource}
     *      *                  enterprises_and_institutions 企事业单位的全称或简称
     *      *                  website 工信部备案网站的全称或简称
     *      *                  app APP应用的全称或简称
     *      *                  public_number_or_small_program 公众号或小程序的全称或简称
     *      *                  store_name 电商平台店铺名的全称或简称
     *      *                  trade_name 商标名的全称或简称
     */
    private String source;
    /**
     * 签名对应的资质证明图片进行 base64 编码格式转换后的字符串,非必填  这里为图片路径
     */
    private String picsPath;
}
