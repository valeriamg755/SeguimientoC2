package controllers.grade;

import domain.mapping.dto.GradeDto;
import domain.mapping.dto.StudentDto;
import domain.mapping.dto.SubjectDto;
import domain.mapping.mappers.GradeMapper;
import domain.mapping.mappers.StudentMapper;
import domain.mapping.mappers.SubjectMapper;
import domain.models.Grade;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.Repository;
import services.GradeService;
import services.StudentService;
import services.SubjectService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "gradeController", value = "/grade-form")
public class GradeController extends HttpServlet {
    @Inject
    @Named("Grade")
    Repository<GradeDto> GradeRepository;
    @Inject
    GradeService service;
    @Inject
    StudentService studentService; ;
    @Inject
    SubjectService subjectService;

    private String message;

    private SubjectDto getSubjectByName(String name) {
        List<SubjectDto> subjects = subjectService.list();
        return subjects.stream()
                .filter(e->e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(null);
    }

    private StudentDto getStudentByName(String name) {
        List<StudentDto> students = studentService.list();
        return students.stream()
                .filter(e->e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(null);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Grades</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String studentName = req.getParameter("students");
        System.out.println(studentName);
        String subjectName = req.getParameter("subjects");
        String gradeName = req.getParameter("grade");
        Map<String, String> errorsmap = getErrors2(studentName, subjectName, gradeName);
        if (errorsmap.isEmpty()) {
            StudentDto studentDto = getStudentByName(studentName);
            System.out.println(studentDto);
            SubjectDto subjectDto = getSubjectByName(subjectName);
            Double grade = Double.parseDouble(req.getParameter("grade"));
            Grade grades = Grade.builder()
                    .student(StudentMapper.mapFrom(studentDto))
                    .subject(SubjectMapper.mapFrom(subjectDto))
                    .grade(grade).build();
            GradeDto gradeDto = GradeMapper.mapFrom(grades);
            service.save(gradeDto);
            System.out.println(service.list());
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Resultado form</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Resultado form!</h1>");
                out.println("        <ul>");
                out.println("            <li>Name: " + studentDto + "</li>");
                out.println("            <li>email: " + subjectDto + "</li>");
                out.println("            <li>email: " + grade + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }
        else {
            req.setAttribute("errorsmap", errorsmap);

            getServletContext().getRequestDispatcher("/grades.jsp").forward(req, resp);
        }
    }
    private Map<String,String> getErrors2(String studentid, String subject, String grade) {
        Map<String,String> errors = new HashMap<>();
        if(studentid==null ||studentid.isBlank()){
            errors.put("student","El student es requerido");
        }
        if(subject==null ||subject.isBlank()){
            errors.put("subject","El subject es requerido");
        }
        if(grade==null ||grade.isBlank()){
            errors.put("grade","El grade es requerido");
        }
        return errors;
    }
}
