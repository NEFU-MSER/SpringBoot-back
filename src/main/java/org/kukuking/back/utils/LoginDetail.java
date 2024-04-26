package org.kukuking.back.utils;

import java.time.LocalDateTime;

public class LoginDetail {
    private final String account;
    private final String name;
    private final LocalDateTime timestamp;

    LoginDetail(String account, String name, int liveTime_Minutes) {
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
    }
}