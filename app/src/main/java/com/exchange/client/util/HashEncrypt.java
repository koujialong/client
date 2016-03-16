package com.exchange.client.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xiangyutian on 16/1/6.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class HashEncrypt {
    private static char[] DigitLower = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static char[] DigitUpper = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final String ALG_MD5 = "MD5";
    public static final String ALG_SHA1 = "SHA-1";
    public static final String ALG_SHA256 = "SHA-256";

    public HashEncrypt() {
    }

    public static String encode(HashEncrypt.CryptType type, String content) {
        MessageDigest instance = null;
        Object encryptMsg = null;

        byte[] var7;
        try {
            if(HashEncrypt.CryptType.MD5 == type) {
                instance = MessageDigest.getInstance("MD5");
            } else if(HashEncrypt.CryptType.SHA1 == type) {
                instance = MessageDigest.getInstance("SHA-1");
            } else if(HashEncrypt.CryptType.SHA256 == type) {
                instance = MessageDigest.getInstance("SHA-256");
            }

            if(instance == null) {
                throw new RuntimeException("instance is null");
            }

            var7 = instance.digest(content.getBytes());
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException("Unbelievabl! How can u passby the method ? No such algorithm !");
        }

        StringBuilder buffer = new StringBuilder();

        for(int i = 0; i < var7.length; ++i) {
            switch(Integer.toHexString(var7[i]).length()) {
                case 1:
                    buffer.append("0");
                    buffer.append(Integer.toHexString(var7[i]));
                    break;
                case 2:
                    buffer.append(Integer.toHexString(var7[i]));
                    break;
                case 8:
                    buffer.append(Integer.toHexString(var7[i]).substring(6, 8));
            }
        }

        return buffer.toString();
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

    public static enum CryptType {
        MD5,
        SHA1,
        SHA256;

        private CryptType() {
        }
    }
}
