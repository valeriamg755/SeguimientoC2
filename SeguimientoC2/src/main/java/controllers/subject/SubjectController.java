package controllers.subject;

import domain.mapping.dto.StudentDto;
import domain.mapping.dto.SubjectDto;
import domain.mapping.dto.TeacherDto;
import domain.mapping.mappers.StudentMapper;
import domain.mapping.mappers.SubjectMapper;
import domain.mapping.mappers.TeacherMapper;
import domain.models.Student;
import domain.models.Subject;
import domain.models.Teacher;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.Repository;
import services.SubjectService;
import services.impl.SubjectServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    @Inject
    @Named("Subject")
    Repository<SubjectDto> SubjectRepository;
    @Inject
    SubjectService service;

    private String message;

    public void init() {
        message = "Subjects";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String teacher = req.getParameter("teacher");
        Subject subject = Subject.builder()
                .name(name)
                .teacher(teacher).build();
        SubjectDto subjectDto = SubjectMapper.mapFrom(subject);
        service.save(subjectDto);
        System.out.println(service.list());
        List<String> errores = getErrors(name, teacher);
        Map<String, String> errorsmap = getErrors2(name, teacher);
        if (errorsmap.isEmpty()) {
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
                out.println("            <li>Name: " + name + "</li>");
                out.println("            <li>Teacher: " + teacher + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }

        else {
            req.setAttribute("errors", errores);
            req.setAttribute("errorsmap", errorsmap);

            getServletContext().getRequestDispatcher("/subject.jsp").forward(req, resp);
        }
    }
    private Map<String,String> getErrors2(String name,String teacher) {
        Map<String,String> errors = new HashMap<>();
        if(name==null ||name.isBlank()){
            errors.put("name","El nombre es requerido");
        }
        if(teacher==null ||teacher.isBlank()){
            errors.put("teacher","El teacher es requerido");
        }
        return errors;
    }
    private List<String> getErrors(String name,String teacher)
    {
        List<String> errors = new ArrayList<String>();
        if(name==null ||name.isBlank()){
            errors.add("El nombre es requerido");
        }
        if(teacher==null ||teacher.isBlank()){
            errors.add("El teacher es requerido");
        }
        return errors;
    }
}
