package icu.saymeevetime.backend.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author jiacheng
 * @date 11/3/23 10:33 pm 星期六
 * @description :
 */

public class Md5Utils {

    public static String encrypt(String source) {
        return encodeMd5(source.getBytes());
    }

    private static String encodeMd5(byte[] source) {
        try {
            return encodeHex(MessageDigest.getInstance("MD5").digest(source));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

    }

    private static String encodeHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buffer.append("0");
            }
            buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buffer.toString();

    }

}