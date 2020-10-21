package dev.remitano.infrastructure.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptUtils {


    public static String hashSHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String hashMD5(String plainText) throws Exception {
        if (plainText == null) {
            throw new NullPointerException();
        }
        byte[] bytes = plainText.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();

        return bytesToHexString(digest);
    }

    public static String bytesToHexString(byte[] bytes) {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) + 0x100).substring(1));
        }

        return sb.toString();
    }

    public static String rsaEncrypt(String publicKey, String plainText) {
        try {
            if (plainText == null) {
                return null;
            }

            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPublicKey key = (RSAPublicKey) factory.generatePublic(new X509EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = plainText.getBytes("UTF-8");
            byte[] cipherBytes = new byte[0];

            int length = plainTextBytes.length;
            int offset = 0;
            while (offset < length) {

                int block_size;
                if (length - offset < 117) {
                    block_size = length - offset;
                } else {
                    block_size = 117;
                }

                byte[] block = new byte[block_size];
                for (int i = 0; i < block_size; ++i) {
                    block[i] = plainTextBytes[offset + i];
                }

                byte[] cipherBlock = cipher.doFinal(block);
                cipherBytes = append(cipherBytes, cipherBlock);

                offset += block_size;
            }

            String cipherText = Base64.getEncoder().encodeToString(cipherBytes);

            return cipherText;
        } catch (Exception ex) {
            return null;
        }
    }

    private static byte[] append(byte[] array1, byte[] array2) {

        int len1 = array1.length;
        int len2 = array2.length;
        byte[] result = new byte[len1 + len2];

        for (int i = 0; i < len1; ++i) {
            result[i] = array1[i];
        }

        for (int i = 0; i < len2; ++i) {
            result[i + len1] = array2[i];
        }

        return result;
    }

    public static String rsaDecrypt(String publicKey, String cipherText) {
        try {
            if (cipherText == null) {
                return null;
            }

            byte[] keyBytes = Base64.getDecoder().decode(publicKey);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPublicKey key = (RSAPublicKey) factory.generatePublic(new X509EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            byte[] plainTextBytes = new byte[0];

            int length = cipherBytes.length;
            int offset = 0;
            while (offset < length) {

                int block_size;
                if (length - offset < 128) {
                    block_size = length - offset;
                } else {
                    block_size = 128;
                }

                byte[] block = new byte[block_size];
                for (int i = 0; i < block_size; ++i) {
                    block[i] = cipherBytes[offset + i];
                }

                byte[] plainTextBlock = cipher.doFinal(block);
                plainTextBytes = append(plainTextBytes, plainTextBlock);

                offset += block_size;
            }

            return new String(plainTextBytes, "UTF-8");
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getChecksum(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String hashSHA256Base64(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] shaByteArr = mDigest.digest(StringUtils.encodeUTF8(input));

        return DatatypeConverter.printBase64Binary(shaByteArr);
    }

    public static String SHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes());
            byte[] mb = md.digest();
            String out = "";
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
            return out;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public static String hashSHA256ToHex(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] shaByteArr = mDigest.digest(StringUtils.encodeUTF8(input));
        return Hex.encodeHexString(shaByteArr);
    }

}
