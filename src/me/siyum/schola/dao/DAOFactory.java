package me.siyum.schola.dao;

import me.siyum.schola.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = (new DAOFactory()) : daoFactory;
    }

    public <T> T getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case PARENT:
                return (T) new ParentDAOImpl();
            case TASKS:
                return (T) new TasksDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case ANNOUNCEMENT:
                return (T) new AnnouncementDAOImpl();
            case EXAMS:
                return (T) new ExamDAOImpl();
            case EXAM_QUESTIONS:
                return (T) new ExamQuestionsDAOImpl();
            case CLASS_ROOMS:
                return (T) new ClassRoomsDAOImpl();
            case SUBJECTS:
                return (T) new SubjectsDAOImpl();
            case CLASSES:
                return (T) new ClassesDAOImpl();
            case BATCHES:
                return (T) new BatchDAOImpl();
            case SALARY:
                return (T) new SalaryDAOImpl();
            case STUDENT_MARK:
                return (T) new StudentMarksDAOImpl();
            case ATTENDANCE:
                return (T) new AttendanceDAOImpl();
            case ATTENDANCE_MARK:
                return (T) new AttendanceMarkDAOImpl();
            case HOME_WORK:
                return (T) new HomeWorkDAOImpl();
            case HOME_WORK_STUDENT:
                return (T) new HomeWorkStudentDAOImpl();
            default:
                return null;
        }
    }

}
