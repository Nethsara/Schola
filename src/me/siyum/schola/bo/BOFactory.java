package me.siyum.schola.bo;

import me.siyum.schola.bo.custom.ParentBO;
import me.siyum.schola.bo.custom.impl.ParentBOImpl;
import me.siyum.schola.bo.custom.impl.StudentBOImpl;
import me.siyum.schola.dao.custom.impl.ParentDAOImpl;

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
            default:
                return null;
        }
    }

}
