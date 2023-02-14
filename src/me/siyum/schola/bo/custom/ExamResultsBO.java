package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.StudentMarkDTO;

public interface ExamResultsBO extends SuperBO {
    boolean saveResults(StudentMarkDTO studentMarkDTO);
}
