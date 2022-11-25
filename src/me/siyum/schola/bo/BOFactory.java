package me.siyum.schola.bo;

import me.siyum.schola.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = (new BOFactory()) : boFactory;
    }

    public <T> T getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case PARENT:
                return (T) new ParentBOImpl();
            case TASKS:
                return (T) new TasksBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case ANNOUNCEMENTS:
                return (T) new AnnouncementBOImpl();
            case EXAMS:
                return (T) new ExamsBoImpl();
            case EXAM_QUESTIONS:
                return (T) new ExamsQuestionsBoImpl();
            case SUBJECTS:
                return (T) new SubjectsBOImpl();
            case CLASS_ROOMS:
                return (T) new ClassRoomsBOImpl();
            case CLASSES:
                return (T) new ClassesBOImpl();
            case BATCHES:
                return (T) new BatchBOImpl();
            case SALARY:
                return (T) new SalaryBOImpl();
            case STUDENT_MARK:
                return (T) new StudentMarkBOImpl();
            default:
                return null;
        }
    }

}
