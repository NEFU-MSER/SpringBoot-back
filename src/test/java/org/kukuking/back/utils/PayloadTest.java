package org.kukuking.back.utils;

import org.junit.Test;
import org.kukuking.back.component.utils.LoginDetail;

public class PayloadTest {

    @Test
    public void convert() {
        LoginDetail loginDetail = new LoginDetail("2021213196","KUKUKING233",30);
        String outPut = loginDetail.convert();
        System.out.println(outPut);
    }
}