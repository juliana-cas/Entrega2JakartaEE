package controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.dtos.SubjectDto;
import repository.Impl.SubjectRepositoryLogicImpl;
import service.Impl.SubjectServiceImpl;
import service.SubjectService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    private SubjectRepositoryLogicImpl subjectRepository;
    private SubjectService service;

    public SubjectController() {
        subjectRepository = new SubjectRepositoryLogicImpl();
        service = new SubjectServiceImpl(subjectRepository);
    }

    private String message;

    public void init(){
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("h1>Teachers</h1>");
        out.println(service.subjectList());
        out.println("</body></html>");
    }

    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        SubjectDto subject = new SubjectDto(4L, name);
        service.update(subject);
        System.out.println(service.subjectList());

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
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
    public void destroy() {

    }
}
