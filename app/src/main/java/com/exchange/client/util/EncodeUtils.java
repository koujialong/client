package com.exchange.client.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xiangyutian on 16/1/6.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class EncodeUtils {
    private static char[] DigitLower = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static char[] DigitUpper = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public EncodeUtils() {
    }

    public static String encodeBase64(String content) {
        return Base64.encode(content.getBytes());
    }

    public static String encodeBase64(byte[] bytes) {
        return Base64.encode(bytes);
    }

    public static byte[] decodeBase64(String content) throws UnsupportedEncodingException {
        return Base64.decode(content);
    }

    public static String encodeMD5(String content) {
        return cryptMD5(content);
    }

    public static String cryptMD5(String str) {
        return crypt(str, "GBK");
    }

    private static String crypt(String str, String encode) {
        if(str != null && str.length() != 0) {
            StringBuffer hexString = new StringBuffer();

            try {
                MessageDigest e = MessageDigest.getInstance("MD5");
                e.update(str.getBytes(encode));
                byte[] hash = e.digest();

                for(int i = 0; i < hash.length; ++i) {
                    if((255 & hash[i]) < 16) {
                        hexString.append("0" + Integer.toHexString(255 & hash[i]));
                    } else {
                        hexString.append(Integer.toHexString(255 & hash[i]));
                    }
                }
            } catch (Exception var6) {
                return "";
            }

            return hexString.toString();
        } else {
            return str;
        }
    }

    public static String encodeSHA1(String content) {
        return HashEncrypt.encode(HashEncrypt.CryptType.SHA1, content);
    }

    public static String encodeSHA256(String content) {
        return HashEncrypt.encode(HashEncrypt.CryptType.SHA256, content);
    }

    public static String urlEncode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "utf-8").replaceAll("\\+", "%20").replaceAll("%7E", "~").replaceAll("\\*", "%2A");
    }

    public static String urlDecode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, "utf-8");
    }

    public static String getMD5Lower(String srcStr) {
        String sign = "lower";

        try {
            return processStr(srcStr, sign);
        } catch (NoSuchAlgorithmException var3) {
            ;
        } catch (NullPointerException var4) {
            ;
        }

        return "";
    }

    public static String getMD5Upper(String srcStr) {
        String sign = "upper";

        try {
            return processStr(srcStr, sign);
        } catch (NoSuchAlgorithmException var3) {
            ;
        } catch (NullPointerException var4) {
            ;
        }

        return "";
    }

    private static String processStr(String srcStr, String sign) throws NoSuchAlgorithmException, NullPointerException {
        String algorithm = "MD5";
        String result = "";
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(srcStr.getBytes());
        byte[] byteRes = digest.digest();
        int length = byteRes.length;

        for(int i = 0; i < length; ++i) {
            result = result + byteHEX(byteRes[i], sign);
        }

        return result;
    }

    private static String byteHEX(byte bt, String sign) {
        Object temp = null;
        char[] temp1;
        if(sign.equalsIgnoreCase("lower")) {
            temp1 = DigitLower;
        } else {
            if(!sign.equalsIgnoreCase("upper")) {
                throw new RuntimeException("");
            }

            temp1 = DigitUpper;
        }

        char[] ob = new char[]{temp1[bt >>> 4 & 15], temp1[bt & 15]};
        return new String(ob);
    }
}
