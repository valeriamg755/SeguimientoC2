package controllers.grade;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.mapping.dto.GradeDto;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.Repository;
import services.GradeService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "gradeId", value="/grade-formId")
public class GradeId extends HttpServlet {
    @Inject
    @Named("gradeRep")
    private Repository<GradeDto> gradeRepository;
    @Inject

    private GradeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GradeDto gradeDto = mapper.readValue(JsonStream, GradeDto.class);
        Long id = gradeDto.id();
        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Grades</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");

    }
}
