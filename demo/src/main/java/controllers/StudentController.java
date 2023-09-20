package controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.dtos.StudentDto;
import repository.Impl.StudentRepositoryLogicImpl;
import service.Impl.StudentServiceImpl;
import service.StudentService;

import java.io.IOException;
import java.io.PrintWriter;

public class StudentController extends HttpServlet {

    private StudentRepositoryLogicImpl studentRepository;
    private StudentService service;

    public StudentController(){
        studentRepository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }

    private String message;

    public void init(){
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("h1>Students</h1>");
        out.println(service.studentList());
        out.println("</body></html>");
    }

    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String career = req.getParameter("career");
        String semester = req.getParameter("semester");
        StudentDto student = new StudentDto(4L,name,email,career,semester);
        service.update(student);
        System.out.println(service.studentList());

        try(PrintWriter out = resp.getWriter()) {

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
            out.println("            <li>Email: " + email + "</li>");
            out.println("            <li>Degree: " + career + "</li>");
            out.println("            <li>Semester: " + semester + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
    public void destroy(){

    }
}