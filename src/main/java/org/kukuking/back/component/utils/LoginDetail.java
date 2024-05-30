package org.kukuking.back.component.utils;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDetail {
    private final String account;
    private final String id;
    private final LocalDateTime timestamp;

    public LoginDetail(String account, String id, int liveTime_Minutes) {
        this.account = account;
        this.id = id;
        this.timestamp = LocalDateTime.now().plusMinutes(liveTime_Minutes).withNano(0);
    }

    public String convert() {
        String result = "";
        result += "{\"account\": \"" + this.account + "\", ";
        result += "\"id\": \"" + this.id + "\", ";
        result += "\"expires_at\": \"" + this.timestamp + "\"}";
        return result;
    }
}