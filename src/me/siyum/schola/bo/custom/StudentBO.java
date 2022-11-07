package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.entity.Student;

public interface StudentBO {

    void save(StudentDTO student);
}
