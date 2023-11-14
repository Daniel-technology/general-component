package com.general.component.wechat.core.constants;

/**
 * @description: url信息
 * @author: liuyan
 * @create: 2023−02-17 4:22 PM
 */
public class UrlConstants {
    /**
     * @Fields WX_GZH_TOKEN_URL : 获取微信公众号的token
     */
    public static final String PUBLICACCOUNT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * @Fields WX_XCX_TOKEN_URL : 获取小程序得token
     */
    public static final String MINIPROGRAM_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

}
