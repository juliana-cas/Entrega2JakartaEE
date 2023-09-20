package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value="/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Establece el tipo de contenido de la respuesta como HTML
        resp.setContentType("text/html");

        // Puede utilizar el método HTTP para determinar la acción que se debe realizar.
        String metodoHttp = req.getMethod(); // Ejemplo: "GET"

        // Puede utilizar la URI de la solicitud para enrutar solicitudes o realizar acciones específicas.
        String requestUri = req.getRequestURI(); // Ejemplo: "/test"

        // Puede usar la URL completa de la solicitud para generar enlaces absolutos en su aplicación.
        String requestUrl = req.getRequestURL().toString(); // Ejemplo: "http://localhost:8080/yourwebapp/test"

        // Puede utilizar el contexto de la aplicación para construir rutas de recursos.
        String contexPath = req.getContextPath(); // Ejemplo: "/yourwebapp"

        // Puede utilizar el servlet path para determinar la ubicación del controlador en su aplicación.
        String servletPath = req.getServletPath(); // Ejemplo: "/test"

        // Puede rastrear la dirección IP del cliente para el registro de actividades o la seguridad.
        String ipCliente = req.getRemoteAddr(); // Ejemplo: "192.168.1.100"

        // Puede usar la dirección IP local y el puerto para fines de configuración o registro.
        String ip = req.getLocalAddr(); // Ejemplo: "127.0.0.1"
        int port = req.getLocalPort(); // Ejemplo: 8080

        // Puede utilizar el esquema para construir URLs absolutas o determinar si la conexión es segura (HTTPS).
        String scheme = req.getScheme(); // Ejemplo: "http" o "https"

        // Puede utilizar el encabezado "Host" para gestionar múltiples dominios o realizar acciones basadas en el host.
        String host = req.getHeader("host"); // Ejemplo: "localhost:8080"

        // Puede construir una URL completa utilizando los componentes anteriores para redirigir o generar enlaces.
        String url = scheme + "://" + host + contexPath + servletPath; // Ejemplo: "http://localhost:8080/yourwebapp/test"

        // Puede construir otra URL completa que incluye la dirección IP y el puerto para fines de depuración o configuración.
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath; // Ejemplo: "http://127.0.0.1:8080/yourwebapp/test"

        try (PrintWriter out = resp.getWriter()) {
            ((PrintWriter) out).println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Cabeceras HTTP Request</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>");

            // Enumera todas las cabeceras HTTP de la solicitud para realizar acciones personalizadas
            // basadas en encabezados específicos o para verificar la información de autenticación, como "Authorization".
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>"+ cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }

            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}

