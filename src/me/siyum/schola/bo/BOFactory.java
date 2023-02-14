package me.siyum.schola.bo;

import me.siyum.schola.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = (new BOFactory()) : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return (SuperBO) new StudentBOImpl();
            case PARENT:
                return new ParentBOImpl();
            case TASKS:
                return new TasksBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ANNOUNCEMENTS:
                return new AnnouncementBOImpl();
            case EXAMS:
                return new ExamsBoImpl();
            case EXAM_QUESTIONS:
                return new ExamsQuestionsBoImpl();
            case SUBJECTS:
                return new SubjectsBOImpl();
            case CLASS_ROOMS:
                return new ClassRoomsBOImpl();
            case CLASSES:
                return new ClassesBOImpl();
            case BATCHES:
                return new BatchBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case STUDENT_MARK:
                return new StudentMarkBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case ATTENDANCE_MARK:
                return new AttendanceMarkBOImpl();
            case HOME_WORK:
                return new HomeWorkBOImpl();
            case HOME_WORK_STUDENT:
                return new HomeWorkStudentBOImpl();
            case LECTURER_VOTE:
                return new LecturerVoteBOImpl();
            case LECTURER_SCHOLA:
                return new LecturerScholaBOImpl();
            case NOTIFICATION:
                return new NotificationBOImpl();
            case NOTIFICATION_STUDENT:
                return new NotificationStudentBOImpl();
            case FEE:
                return new FeeBOImpl();
            case USERS:
                return new UsersBOImpl();
            case ATTENDANCE_MARKING:
                return new AttendanceMarkingBOImpl();
            case CLASS_SCHEDULER:
                return new ClassSchedulerBOImpl();
            case EXAM_RESULT:
                return new ExamResultsBOImpl();
            case SAVE_STUDENTS:
                return new SaveStudentsBOImpl();
            default:
                return null;
        }
    }

}
