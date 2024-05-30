package org.kukuking.back.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kukuking.back.DO.Course;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course2Occupations {
    private Course course;
    private List<FrontOccupation> occupations;
}
