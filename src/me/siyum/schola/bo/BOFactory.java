package me.siyum.schola.bo;

import me.siyum.schola.bo.custom.impl.StudentBOImpl;

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
            default:
                return null;
        }
    }

}
