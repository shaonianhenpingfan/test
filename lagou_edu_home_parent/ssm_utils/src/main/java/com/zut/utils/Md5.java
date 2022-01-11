package com.zut.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {

    public final static String md5key = "Ms2";

    /**
     * MD5方法加密
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * */
    public static String Md5(String text, String key){
        String encodeStr = DigestUtils.md5Hex(text + key);
        System.out.println("md5加密后的字符串encodeStr" + encodeStr);
        return encodeStr;
    }

    /**
     * md5验证
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * */
    public static boolean verify(String text, String key, String md5){
        String md5Text = Md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)){
            System.out.println("通过验证");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 注册 用户名：tom 密码 123456
        // 添加用户的时候，要进行加密
        String lagou = Md5("123456", "lagou");
        System.out.println(lagou);
        // 登陆 用户名 tom   123456 select * from user where username = tom and password = 123456
        // 1.根据用户名进行查询 f00485441dfb815c75a13f3c3389c0b9
        boolean verify = Md5.verify("123456", "lagou", "f00485441dfb815c75a13f3c3389c0b9");
        System.out.println(verify);
    }

}
