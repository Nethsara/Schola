package me.siyum.schola.bo;

import me.siyum.schola.bo.custom.impl.EmployeeBOImpl;
import me.siyum.schola.bo.custom.impl.ParentBOImpl;
import me.siyum.schola.bo.custom.impl.StudentBOImpl;
import me.siyum.schola.bo.custom.impl.TasksBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?boFactory=(new BOFactory()) : boFactory;
    }

    public <T> T getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return (T) new StudentBOImpl();
            case PARENT:
                return (T) new ParentBOImpl();
            case TASKS:
                return (T) new TasksBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            default:
                return null;
        }
    }

}
