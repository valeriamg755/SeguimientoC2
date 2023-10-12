package controllers.extras;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value = "/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // Establece el tipo de contenido de la respuesta como HTML.
        resp.setContentType("text/html");

        // Obtiene información sobre la solicitud HTTP.
        String metodoHttp = req.getMethod(); // Obtiene el método HTTP (GET, POST, etc.).
        String requestUri = req.getRequestURI(); // Obtiene la URI de la solicitud.
        String requestUrl = req.getRequestURL().toString(); // Obtiene la URL de la solicitud.
        String contexPath = req.getContextPath(); // Obtiene el contexto de la solicitud.
        String servletPath = req.getServletPath(); // Obtiene la ruta del servlet.
        String ipCliente = req.getRemoteAddr(); // Obtiene la dirección IP del cliente.
        String ip = req.getLocalAddr(); // Obtiene la dirección IP local del servidor.
        int port = req.getLocalPort(); // Obtiene el puerto local del servidor.
        String scheme = req.getScheme(); // Obtiene el esquema de la solicitud (http o https).
        String host = req.getHeader("host"); // Obtiene el encabezado 'host' de la solicitud.
        String url = scheme + "://" + host + contexPath + servletPath; // Construye una URL completa.
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath; // Construye otra URL completa.

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Cabeceras HTTP Request</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>");

            // Imprime la información obtenida en la respuesta HTML.
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");

            // Obtiene y muestra todas las cabeceras HTTP de la solicitud.
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
