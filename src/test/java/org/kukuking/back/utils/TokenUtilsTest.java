package org.kukuking.back.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kukuking.back.component.utils.LoginDetail;
import org.kukuking.back.component.utils.TokenUtils;

@Slf4j
public class TokenUtilsTest {
    final LoginDetail loginDetail = new LoginDetail("2021213196","KUKUKING",30);
    String token;
    @Test
    public void generateToken() {
        token = TokenUtils.generateToken(loginDetail);
        System.out.println(token);
    }

    @Test
    public void verifyToken() {
        token = TokenUtils.generateToken(loginDetail);
        System.out.println("true token = " + token);
        boolean result = TokenUtils.verifyToken(token);
        System.out.println("true result= " + result);
        String[] split = token.split("\\.");
        LoginDetail fakeDetail = new LoginDetail("2021213196","KUKUKING",50);
        //非法长度输入测试
        String badToken = fakeDetail.convert();
        System.out.println("bad  token = " + badToken);
        boolean badResult = TokenUtils.verifyToken(badToken);
        System.out.println("bad  result= " + badResult);
        //假token测试
        String fakeToken = fakeDetail.convert() + '.' + split[1];
        System.out.println("fake token = " + fakeToken);
        boolean fakeResult = TokenUtils.verifyToken(fakeToken);
        System.out.println("fake result= " + fakeResult);
        //
        String tempToken = "{\"account\": \"2021213196\", \"username\": \"KUKUKING\", \"expires_at\": \"2024-05-27T18:53:34\"}.KuOcWcgCo3Om4vX6rkaQVRRFGhdezFwpA6Qs0RocVD0=";
        System.out.println("temp token = " + tempToken);
        boolean result1 = TokenUtils.verifyToken(tempToken);
        System.out.println("temp result= " + result1);
    }

    @Test
    public void get(){
        token = TokenUtils.generateToken(loginDetail);
        String account = TokenUtils.getAccount(token);
        log.debug(account);
    }
}