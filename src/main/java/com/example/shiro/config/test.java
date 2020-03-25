package com.example.shiro.config;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author:congguangbo
 * @Date:2020/3/8 16:20
 */
public class test {
    public static void main(String[] args) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
        //盐值
        String salt = "cgb";
        //原密码
        String pwd = "123456";
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
        System.out.println(result);

    }
}
