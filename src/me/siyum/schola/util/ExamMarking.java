package me.siyum.schola.util;

import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AttendanceMarkBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.bo.custom.StudentMarkBO;
import me.siyum.schola.dto.AttendanceMarkDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.StudentMarkDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamMarking {
    public static int mark = 0;
    public static int currentQ = 1;
    public static String examID = "e1";
    public static String stID = "";
    public static int markPerQuestion = 0;

    public static boolean scholaReload() throws SQLException, ClassNotFoundException {

        boolean saved = true;
        StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
        StudentMarkBO studentMarkBO = BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);
        AttendanceMarkBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE_MARK);
        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents("");

        for (StudentDTO s : studentDTOS
        ) {
            int scholaMark = 0;
            double exmMarks = 0;
            double attendanceMark = 0;

            ArrayList<StudentMarkDTO> marksByID = studentMarkBO.getMarksByID(s.getId());
            for (StudentMarkDTO sm : marksByID
            ) {
                exmMarks += sm.getMark();
            }


            scholaMark += ((exmMarks / marksByID.size()) * 50) / 100;
            System.out.println("Student " + s.getId() + " scho mark  " + scholaMark);


            scholaMark += 20; //20% from home works

            ArrayList<AttendanceMarkDTO> attendanceByID = attendanceBO.getAttendanceByID(s.getId());
            for (AttendanceMarkDTO amdt : attendanceByID
            ) {
                if (amdt.isPresent()) {
                    attendanceMark++;
                }
            }
            scholaMark += ((attendanceMark / attendanceByID.size()) * 30) / 100;

            saved = studentBO.updateStudent(
                    new StudentDTO(
                            s.getId(),
                            s.getName(),
                            s.getEmail(),
                            s.getNic(),
                            s.getImage(),
                            s.getAddress(),
                            s.getPhone(),
                            s.getParentID(),
                            scholaMark,
                            s.getDob(),
                            true,
                            true,
                            s.getBatch()

                    )
            );

        }
        return saved;
    }
}
