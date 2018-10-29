package com.xiyoumoblie.module.education.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by heshu on 2018/10/15.
 */

public class Tool {

   //加密，需要公钥
    public static byte[] encrypt(byte[] ptext,String modulus, String exponent) throws Exception {
        // 获取公钥及参数e,n
        RSAPublicKey pbk =getPublicKey(modulus,exponent);
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();

        // 获取明文m
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    private static RSAPublicKey getPublicKey(String modulus, String exponent) {
        try {
            BigInteger b1 = new BigInteger(modulus);
            BigInteger b2 = new BigInteger(exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toUTF8(String str) {
        if (isEmpty(str)) {
            return "";
        }
        try {
            if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
                str = new String(str.getBytes("GB2312"), "utf-8");
                return str;
            }
        } catch (Exception exception) {
        }
        try {
            if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                str = new String(str.getBytes("ISO-8859-1"), "utf-8");
                return str;
            }
        } catch (Exception exception1) {
        }
        try {
            if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
                str = new String(str.getBytes("GBK"), "utf-8");
                return str;
            }
        } catch (Exception exception3) {
        }
        return str;
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        // 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
        if (str != null && !str.trim().isEmpty()) {
            return false;// 不为空
        }
        return true;// 为空
    }

}
