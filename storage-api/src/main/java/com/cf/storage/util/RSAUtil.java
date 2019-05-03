package com.cf.storage.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/** 
 * <p>类名称     ：com.fst.security.util.RSAUtil</p>
 * <p>描述          ：  RSA公钥/私钥/签名工具包
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月27日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class RSAUtil {

    /** */
    /**
    * 加密算法RSA
    */
    public static final String KEY_ALGORITHM = "RSA";

    /** */
    /**
    * 签名算法
    */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** */
    /**
    * 获取公钥的key
    */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** */
    /**
    * 获取私钥的key
    */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
    * RSA最大加密明文大小
    */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
    * RSA最大解密密文大小
    */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /** 
    * <p>方法名     :genKeyPair </p> 
    * <p>方法描述: 生成密钥对(公钥和私钥)</p> 
    * <p>逻辑描述: </p> 
    * @param length
    * @return
    * @throws Exception 
    */
    public static Map<String, Object> genKeyPair(Integer length) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(length == null ? 1024 : length);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /** */
    /**
    * <p>
    * 用私钥对信息生成数字签名
    * </p>
    *
    * @param data 已加密数据
    * @param privateKey 私钥(BASE64编码)
    *
    * @return
    * @throws Exception
    */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }
    

    /** */
    /**
    * <p>
    * 校验数字签名
    * </p>
    *
    * @param data 已加密数据
    * @param publicKey 公钥(BASE64编码)
    * @param sign 数字签名
    *
    * @return
    * @throws Exception
    *
    */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /** */
    /**
    * <P>
    * 私钥解密
    * </p>
    *
    * @param encryptedData 已加密数据
    * @param privateKey 私钥(BASE64编码)
    * @return
    * @throws Exception
    */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            }
            else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
    * <p>
    * 公钥解密
    * </p>
    *
    * @param encryptedData 已加密数据
    * @param publicKey 公钥(BASE64编码)
    * @return
    * @throws Exception
    */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            }
            else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
    * <p>
    * 公钥加密
    * </p>
    *
    * @param data 源数据
    * @param publicKey 公钥(BASE64编码)
    * @return
    * @throws Exception
    */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            }
            else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
    * <p>
    * 私钥加密
    * </p>
    *
    * @param data 源数据
    * @param privateKey 私钥(BASE64编码)
    * @return
    * @throws Exception
    */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            }
            else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
    * <p>
    * 获取私钥
    * </p>
    *
    * @param keyMap 密钥对
    * @return
    * @throws Exception
    */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /** */
    /**
    * <p>
    * 获取公钥
    * </p>
    *
    * @param keyMap 密钥对
    * @return
    * @throws Exception
    */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    /** 
    * <p>方法名     :main </p> 
    * <p>方法描述: 测试公私钥加解密及签名验签</p> 
    * <p>逻辑描述: 需要注意的是，本测试只生成了一对公私钥，ssl应该是双向认证，需要交换公钥，客户端应该使用服务器的公钥，服务器应该有客户端的私钥</p> 
    * @param args
    * @throws Exception 
    */ 
    public static void main(String[] args) throws Exception {
        Map<String,Object> keyMap = genKeyPair(null);
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        System.out.println("公钥：\r\n" +publicKey);
        System.out.println("私钥：\r\n" +privateKey);
        System.out.println("===================================");
        String source = "用来测试公私钥加解密的字符串";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encryptedData = RSAUtil.encryptByPublicKey(data, publicKey);
        System.out.println("加密后：\r\n" + new String(encryptedData));
        byte[] decoderStr = RSAUtil.decryptByPrivateKey(encryptedData, privateKey);
        System.out.println("解密后：\r\n" + new String(decoderStr));
        System.out.println("===================================");
        String signStr = RSAUtil.sign(encryptedData, privateKey);
        System.out.println("加密后的签名值：\r\n" + signStr);
        Boolean vialdSign = RSAUtil.verify(encryptedData, publicKey, signStr);
        System.out.println("验签结果：\r\n" + vialdSign);
    }
    
}
