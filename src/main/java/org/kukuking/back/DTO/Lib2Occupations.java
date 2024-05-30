package org.kukuking.back.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kukuking.back.DO.Lib;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lib2Occupations {
    private Lib lib;
    private List<FrontOccupation> occupations;
}
