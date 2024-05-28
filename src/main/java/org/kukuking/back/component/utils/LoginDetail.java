package org.kukuking.back.component.utils;

import java.time.LocalDateTime;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDetail {
    private final String account;
    private final String name;
    @JSONField(name = "expires_at")
    private final LocalDateTime timestamp;

    public LoginDetail(String account, String name, int liveTime_Minutes) {
        this.account = account;
        this.name = name;
        this.timestamp = LocalDateTime.now().plusMinutes(liveTime_Minutes).withNano(0);
    }

    public String convert() {
        String result = "";
        result += "{\"account\": \"" + this.account + "\", ";
        result += "\"username\": \"" + this.name + "\", ";
        result += "\"expires_at\": \"" + this.timestamp + "\"}";
        return result;
//        return JSON.toJSONString(this);
    }
//
//    public static LoginDetail parse(String json) {
//        return JSON.parseObject(json, LoginDetail.class);
//    }
}