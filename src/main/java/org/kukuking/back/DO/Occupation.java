package org.kukuking.back.DO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Occupation {
    @Id
    @CreatedBy
    private String id;

    private String libId;
    private String courseId;
    private String userId;

    private int startWeek;
    private int endWeek;
    private int day;
    private int startClass;
    private int endClass;

    @ReadOnlyProperty
    private LocalDateTime createTime;
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}
