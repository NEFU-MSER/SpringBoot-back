package org.kukuking.back.component;

import lombok.*;

import java.util.Map;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqData<T> {
    private String token;
    private T data;
}
