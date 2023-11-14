package com.general.component.common.utils;

import com.general.component.common.jackson.JacksonSupport;
import com.general.component.common.license.core.LicenseExtra;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @description: AES 对称算法加密/解密工具类
 * @author: littlesnail
 * @create: 2023−02-24 11:21 AM
 */
public class AESUtil {


    /**
     * 加解密统一编码方式
     */
    private final static String ENCODING = "utf-8";

    /**
     * 加解密方式
     */
    private final static String ALGORITHM = "AES";

    /**
     * 加密模式及填充方式
     */
    private final static String PATTERN = "AES/ECB/pkcs5padding";

    /**
     * AES加密
     *
     * @param plainText
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText, String key) throws Exception {
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        SecretKey secretKey = new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM);
        // AES加密采用pkcs5padding填充
        Cipher cipher = Cipher.getInstance(PATTERN);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //执行加密操作
        byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
        return Base64.getEncoder().encodeToString(encryptData);
    }

    public static void main(String[] args) throws Exception {
        String a = SecretKeyUtil.generateAESKey();
        LicenseExtra licenseExtra = new LicenseExtra("1",2.0);
        String aaa = encrypt(JacksonSupport.objToJsonStr(licenseExtra), a);
        String bb = decrypt(aaa, a);
        System.out.println(1);
    }


    /**
     * AES解密
     *
     * @param plainText
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String plainText, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM);
        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(PATTERN);
        // 初始化密码器（解密模型）
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 解密数据, 返回明文
        byte[] encryptData = cipher.doFinal(Base64.getDecoder().decode(plainText));
        return new String(encryptData, ENCODING);
    }

}
