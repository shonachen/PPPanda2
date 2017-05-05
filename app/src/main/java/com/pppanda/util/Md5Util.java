package com.pppanda.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加密工具
 */
public final class Md5Util {

    public static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static final String MD5 = "MD5";

    private Md5Util() {
    }

    /**
     * 对字符串进行md5加密
     * @param s 原字符串
     * @return 加密后的字符串
     */
    public static String md5(String s) {

        MessageDigest sMd5MessageDigest = null;
        try {
            sMd5MessageDigest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (sMd5MessageDigest == null) {
            return "";
        }

        sMd5MessageDigest.reset();
        try {
            sMd5MessageDigest.update(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
        }

        byte digest[] = sMd5MessageDigest.digest();

        StringBuilder sStringBuilder = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            final int b = digest[i] & 255;
            if (b < 16) {
                sStringBuilder.append('0');
            }
            sStringBuilder.append(Integer.toHexString(b));
        }

        String ss = sStringBuilder.toString();
        sStringBuilder.delete(0, sStringBuilder.length() - 1);
        return ss;
    }

    /**
     * 将文件进行MD5加密
     * @param path 文件路径
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String md5ForFile(String path) throws Exception {
        File file = new File(path);
        
        return md5ForFile(file);
    }

    /**
     * 将文件进行MD5加密
     * @param file 要加密的文件
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String md5ForFile(File file) throws Exception {
        MessageDigest md5 = null;
   
        InputStream fis;
        fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[1024];
        md5 = MessageDigest.getInstance(MD5);
        int numRead = 0;

        if (md5 == null || md5.digest() == null) {
            return "";
        }

        while ((numRead = fis.read(buffer)) > 0) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();

        if (md5 == null || md5.digest() == null) {
            return "";
        }

        return toHexString(md5.digest());
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_CHARS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_CHARS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

}
