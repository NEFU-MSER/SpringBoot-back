package org.kukuking.back.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kukuking.back.DO.Occupation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FrontOccupation {
    private String id;

    private String libId;
    private String courseId;

    private String libName;
    private String courseName;

    private int[] week;
    private int day;
    private int[] time;

    public FrontOccupation(String id, String libId, String courseId, String libName, String courseName, Occupation occupation) {
        this.id = id;
        this.libId = libId;
        this.courseId = courseId;
        this.libName = libName;
        this.courseName = courseName;
        this.week = new int[]{occupation.getStartWeek(), occupation.getEndWeek()};
        this.day = occupation.getDay();
        this.time = new int[]{occupation.getStartTime(), occupation.getEndTime()};
    }
}
