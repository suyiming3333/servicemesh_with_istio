package com.corn.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: RsaUtil
 * @description: TODO
 * @date 2022/9/2 10:26
 */
public class RsaUtils {

    private static String algorithm = "RSA";

    /**
     * 获取 私钥对象
     * @param key 私钥字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //对key进行base64加密
        byte[] decode = Base64.getDecoder().decode(key);
        //使用PKCS8进行加密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
        //加密方式RSA
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //生成privateKey
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 获取公钥对象
     * @param key 公钥字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeySpecException {
        // 对key进行base64加密
        byte[] decode = Base64.getDecoder().decode(key);
        //公钥使用X509进行加密
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
        //加密方式RSA
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //生成publicKey
        return keyFactory.generatePublic(spec);
    }


    /**
     * 从文件中读取密钥
     * @param filename 传入文件名，相对于classpath
     * @return
     * @throws IOException
     */
    private static byte[] readFile(String filename) throws IOException {
        return Files.readAllBytes(new File(filename).toPath());
    }


    /**
     * 通过publicKey 生成jwk
     * @param publicKey
     * @return
     */
    private static Map<String, Object> generateJWK(PublicKey publicKey){

        RSAPublicKey rsa = (RSAPublicKey) publicKey;

        Map<String, Object> values = new HashMap<>();

        values.put("kty", rsa.getAlgorithm()); // getAlgorithm() returns kty not algorithm
        values.put("kid", "msistio-jwt-kid");
        values.put("n", Base64.getUrlEncoder().encodeToString(rsa.getModulus().toByteArray()));
        values.put("e", Base64.getUrlEncoder().encodeToString(rsa.getPublicExponent().toByteArray()));
        values.put("alg", "RS256");
        values.put("use", "sig");

        return values;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUTRLDiPisaWf+oVWygm+T7g6AxhfrzWnsUVqLYrYbDDwza2CKfhxRk0O/B1A0EACJlbhGdeF0//TX+f0GmSAvMNEx2yAZy8sMRnLjDxpmY19FO0cj3Ie8UN6wIpKq6dWBXpkPv2+EE1OQviJfGbs+rkgy9DpTcs/mcEmXIOsmhwIDAQAB";
        byte[] decoded = Base64.getDecoder().decode(publickey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").
                generatePublic(new X509EncodedKeySpec(decoded));
        generateJWK(pubKey);
        System.out.println(1);
    }
}
