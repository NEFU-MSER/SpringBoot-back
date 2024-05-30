package org.kukuking.back.DO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kukuking.back.DTO.FrontOccupation;
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
    private int startTime;
    private int endTime;

    @ReadOnlyProperty
    private LocalDateTime createTime;
    @ReadOnlyProperty
    private LocalDateTime updateTime;

    public Occupation(FrontOccupation frontOccupation){
        this.id = frontOccupation.getId();
        this.libId = frontOccupation.getLibId();
        this.courseId = frontOccupation.getCourseId();
        int[] week = frontOccupation.getWeek();
        int[] time = frontOccupation.getTime();
        this.startWeek = week[0];
        this.endWeek = week[1];
        this.day = frontOccupation.getDay();
        this.startTime = time[0];
        this.endTime = time[1];
    }
}
