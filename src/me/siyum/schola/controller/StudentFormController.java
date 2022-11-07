package me.siyum.schola.controller;

import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.StudentDTO;

public class StudentFormController {

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void addStudent(){
        studentBO.save(new StudentDTO());
    }
}

