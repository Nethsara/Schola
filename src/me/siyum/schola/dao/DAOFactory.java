package me.siyum.schola.dao;

import me.siyum.schola.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = (new DAOFactory()) : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PARENT:
                return new ParentDAOImpl();
            case TASKS:
                return new TasksDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ANNOUNCEMENT:
                return new AnnouncementDAOImpl();
            case EXAMS:
                return new ExamDAOImpl();
            case EXAM_QUESTIONS:
                return new ExamQuestionsDAOImpl();
            case CLASS_ROOMS:
                return new ClassRoomsDAOImpl();
            case SUBJECTS:
                return new SubjectsDAOImpl();
            case CLASSES:
                return new ClassesDAOImpl();
            case BATCHES:
                return new BatchDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case STUDENT_MARK:
                return new StudentMarksDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case ATTENDANCE_MARK:
                return new AttendanceMarkDAOImpl();
            case HOME_WORK:
                return new HomeWorkDAOImpl();
            case HOME_WORK_STUDENT:
                return new HomeWorkStudentDAOImpl();
            case LECTURER_VOTE:
                return new LecturerVoteDAOImpl();
            case LECTURER_SCHOLA:
                return new LecturerScholaDAOImpl();
            case NOTIFICATION:
                return new NotificationDAOImpl();
            case NOTIFICATION_STUDENT:
                return new NotificationStudentDAOImpl();
            case FEE:
                return new FeeDAOImpl();
            case USERS:
                return new UsersDAOImpl();
            default:
                return null;
        }
    }

}
