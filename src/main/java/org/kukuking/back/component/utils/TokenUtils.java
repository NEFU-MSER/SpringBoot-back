package org.kukuking.back.component.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Slf4j
public class TokenUtils {
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final String SECRET_KEY = "KUKUKING-securityKey";

    // 生成 Token
    public static String generateToken(LoginDetail loginDetail) {
        return generateToken(loginDetail.convert());
    }

    public static String generateToken(String loginDetail){
        try {
            // 创建 HMAC-SHA256 密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(secretKeySpec);
            // 计算签名
            byte[] signatureBytes = mac.doFinal(loginDetail.getBytes());
            // 使用 Base64 编码签名
            String signature = Base64.getEncoder().encodeToString(signatureBytes);
            // 返回 Token，格式为 loginDetail.signature
            return loginDetail + "." + signature;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    // 校验 Token
    public static boolean verifyToken(String token) {
        try {
            // 提取载荷和签名
            String[] parts = token.split("\\.");
            String loginDetail = parts[0];
            String receivedSignature = parts[1];
            // 创建 HMAC-SHA256 密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(secretKeySpec);
            // 计算签名
            byte[] calculatedSignatureBytes = mac.doFinal(loginDetail.getBytes());
            String calculatedSignature = Base64.getEncoder().encodeToString(calculatedSignatureBytes);
            // 验证签名是否一致
            if (!receivedSignature.equals(calculatedSignature)) {
                return false;
            }
            // 提取过期时间
            String expirationTime = loginDetail.split("\"expires_at\": \"")[1].split("\"")[0];
            // 检查有效期
            LocalDateTime expiresAt = LocalDateTime.parse(expirationTime);
            LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
            return currentTime.isBefore(expiresAt);
        } catch (NoSuchAlgorithmException | InvalidKeyException | ArrayIndexOutOfBoundsException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public static String getAccount(String token){
        String[] parts = token.split("\\.");
        String loginDetail = parts[0];
        String account = null;
        try {
             account = loginDetail.split("\"account\": \"")[1].split("\"")[0];
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return account;
    }
}
