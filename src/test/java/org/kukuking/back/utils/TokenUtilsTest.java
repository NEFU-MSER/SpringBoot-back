package org.kukuking.back.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TokenUtilsTest {
    LoginDetail loginDetail = new LoginDetail("2021213196","KUKUKING233",30);
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
        LoginDetail fakeDetail = new LoginDetail("2021213196","KUKUKING233",50);
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
    }


}