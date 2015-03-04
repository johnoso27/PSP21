package edu.uniandes.ecos.psp21.vista;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase que permite el cálculo de la integral a travéz de una interfaz web.
 *
 * @author JohnDany
 */
public class VistaWeb {

    /**
     * Métod que muestra la página de inicio de la aplicación.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @throws ServletException
     * @throws IOException generada por entrada y salida de datos.
     */
    public static void mostrarFormunlarioInicial(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.write("<head><title>John Dany Osorio R</title></head>");
        pw.println("<h1>Programa PSP 2.1</h1>");
        pw.write("<form  method=\"post\">\n"
                + "<div><h2>Método que calcula el valor de x para el cual, integrar la función desde 0 a x, dado el valor de p.</h2></div> \n"
                + "<div><input style=\"width:200;\" id=\"btnTest\" name=\"btnTest\" type=\"submit\" value=\"Calcular dato de prueba\"></div>\n"
                + "<div><input style=\"width:200;\"  id=\"btnEntrada\" name=\"btnEntrada\" type=\"submit\" value=\"Calcular datos personalizados\"></div>\n"
                + "</form> ");
        pw.write("</html>");
    }

    /**
     * Método que muestra los resultados del conteo
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @param resultado del conteo de líneas de código fue
     * @param segmento para subdividir el rango la integral.
     * @param x límite de la integral.
     * @param e margen de error.
     * @param dof (Degrees of freedom). Grados de libertad.
     * @param titulo a mostrar en la página
     * @throws ServletException excepción genera por el servlet
     * @throws IOException excepciones de entrada y salidad de datos.
     */
    public static void mostrarResultados(HttpServletRequest req, HttpServletResponse resp, Double resultado, Integer segmento, Double x, Double e, Double dof, String titulo)
            throws ServletException, IOException {
        String integral = String.format("%.5f", resultado);
        String margen = String.format("%.7f", e);

        PrintWriter r = resp.getWriter();
        r.println("<br><h3>" + titulo + "</h3>");
        r.println("<html>");
        r.write("<head>");
        r.write("<title>John Dany Osorio R</title>");
        r.write("<meta http-equiv=\"Content-Language\" >");
        r.write("<meta http-equiv=\"Content-Type\" content=\"text/html;\">");
        r.println("<style>");
        r.println("table, th, td");
        r.print("{");
        r.println("border: 1px solid black;");
        r.println("border-collapse: collapse;");
        r.println("}");
        r.println("th, td");
        r.print("{");
        r.println("padding: 5px;");
        r.println("text-align: left;");
        r.print("}");
        r.println("</style>");
        r.write("</head>");
        r.write("<form  method=\"post\">");
        r.write("<body>");
        r.println("<table>");
        r.println("<tr>");
        r.println("<th>Valor de X</th>");
        r.println("</tr>");
        r.println("<tr>");
        r.println("<td><label>" + integral + " </label>");
        r.println("</tr>");
        r.println("<tr>");
        r.println("</tr>");
        r.println("<td colspan=\"6\">");
        r.println("<h4>Valores base: </h4><label>p: " + x + "</br>Segmento: " + segmento + "</br>E: " + margen + "</br>Dof: " + dof + "</label>");
        r.println("</td>");
        r.println("</table>");
        r.println("<div><input style=\"width:100;\"  id=\"btnInicio\" name=\"btnInicio\" type=\"submit\" value=\"Regrear\"></div>");
        r.println("</body>");
        r.println("</form>");
        r.println("</html>");
        r.flush();
    }

    /**
     * Método que muestra el formulario para la captura de datos para calcualar
     * la integral numérica.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página.
     * @throws IOException excepción generada.
     */
    public static void mostrarVentanaEntrada(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<head><title>John Dany Osorio R</title></head>");
        pw.println("<h1>Captura de datos</h1>");
        pw.write("<form  method=\"post\">\n"
                + "<div><h2>Cálculo de la integral numérica de 0 a x</h2></div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >X:</label><input  style=\"width:300;\" title=\"Es un valor doble\" type=\"text\" name=\"datosx\" id=\"datosx\"></div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >Segmentos:</label><input  style=\"width:300;\" title=\"Es un valor entero\" type=\"text\" name=\"segmento\" id=\"segmento\"</div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >Margen error (E):</label><input  style=\"width:300;\" title=\"Es un valor doble\" type=\"text\" name=\"error\" id=\"error\"</div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >Grados de libertad (dof):</label><input  style=\"width:300;\" title=\"Es un valor doble\" type=\"text\" name=\"dof\" id=\"dof\"</div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >Valor esperado (p):</label><input  style=\"width:300;\" title=\"Es un valor doble\" type=\"text\" name=\"btnp\" id=\"btnp\"</div> \n"
                + "<div><input id=\"btnCalcularEntrada\" name=\"btnCalcularEntrada\" type=\"submit\" value=\"Calcular\" text=\"\"></div>\n"
                + "</form> ");
        pw.write("</html>");
    }

    /**
     * Método que controla la presentación de errores.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @param ex excepción capturada
     * @throws ServletException
     * @throws IOException
     */
    public static void error(HttpServletRequest req, HttpServletResponse resp, Exception ex)
            throws ServletException, IOException {
        resp.getWriter().println("Vaya se ha presentado un error: " + ex.getMessage() + ".");
    }
}
